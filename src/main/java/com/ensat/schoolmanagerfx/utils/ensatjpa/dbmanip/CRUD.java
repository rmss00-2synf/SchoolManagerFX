package com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.DatabaseConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CRUD<T,PK> {

    Connection connection = DatabaseConnection.getInstance().getConnection();

    default boolean save(T entity) {
        return mappingObjectResultSet(entity);
//        try {
//            Class<?> clazz = entity.getClass();
//            String tableName = clazz.getSimpleName().toUpperCase();
//            Field[] fields = clazz.getDeclaredFields();
//            StringBuilder columns = new StringBuilder();
//            StringBuilder placeholders = new StringBuilder();
//            for (Field field : fields) {
//                if (isARelationField(field) && field.isAnnotationPresent(JointureDeColonne.class)){
//
//                }
//                if (field.isAnnotationPresent(JointureDeColonne.class)) {
//                    JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
//                    columns.append(colonne.nom()).append(",");
//                    placeholders.append("?,");
//                } else if (!field.isAnnotationPresent(Relation.class)) {
//                    field.setAccessible(true);
//                    columns.append(field.getName()).append(",");
//                    placeholders.append("?,");
//                }
//            }
//            // Supprimer la dernière virgule
//            columns.deleteCharAt(columns.length() - 1);
//            placeholders.deleteCharAt(placeholders.length() - 1);
//            // Générer la requête SQL
//            String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);
//            System.out.println(sql);
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            // Injecter les valeurs dans la requête
//            int index = 1;
//            for (Field field : fields) {
//                if (field.isAnnotationPresent(JointureDeColonne.class) || !isARelationField(field)) {
//                    field.setAccessible(true);
//                    statement.setObject(index++, field.get(entity)); // Récupérer la valeur du champ
//                    System.out.println(field.getName() + " " + field.get(entity));
//                }
//            }
//            statement.executeUpdate();
//            statement.close();
//            System.out.println("Record created in table: " + tableName);
//            return true;
//
//        } catch (SQLException | IllegalAccessException e) {
//            throw new RuntimeException("Error creating record: " + e.getMessage(), e);
//        }
    }
    default boolean update(Object entity) {
        try {
            boolean isJoiColumn = false;
            Class<?> clazz = entity.getClass();
            String original = "Bonjour tout le monde";
            String suffix = "monde";
            String tableName;

            String query = clazz.getSimpleName().toUpperCase();
            if (query.endsWith("DTO")) {
                tableName = query.substring(0, query.length() - 3);
            }
            else tableName = query;



            Field[] fields = clazz.getDeclaredFields();
            StringBuilder setClause = new StringBuilder();
            // Identifier la clé primaire (supposons qu'elle s'appelle "id")
            String idColumn = null;

            for (Field field : fields) {
                field.setAccessible(true);
                if (isARelationField(field) && field.isAnnotationPresent(JointureDeColonne.class)) {
                    JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                    if (colonne.nom().equalsIgnoreCase("id")){
                        idColumn = colonne.nom();
                        isJoiColumn = true;
                    }

                    update(field.get(entity));
                }
                else if (field.isAnnotationPresent(JointureDeColonne.class)) {
                    JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                    if (colonne.nom().equalsIgnoreCase("id")) {
                        idColumn = colonne.nom();
                    } else {
                        setClause.append(colonne.nom()).append(" = ?, ");
                    }

                } else if (!isARelationField(field)) {
                    field.setAccessible(true);
                    if (field.getName().equalsIgnoreCase("id")) {
                        idColumn = field.getName();
                    } else {
                        setClause.append(field.getName()).append(" = ?, ");
                    }
                }
            }

            // Vérifier si la clé primaire est présente
            if (idColumn == null) {
                throw new RuntimeException("L'entité doit avoir un champ 'id' pour identifier la clé primaire.");
            }

            // Supprimer la dernière virgule
            setClause.delete(setClause.length() - 2, setClause.length());

            // Générer la requête SQL
            String sql = String.format("UPDATE %s SET %s WHERE %s = ?", tableName, setClause, idColumn);
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);

            int index = 1;
            System.out.println(index);
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Relation.class) || field.isAnnotationPresent(JointureDeColonne.class)) {
                    field.setAccessible(true);
                    if(field.isAnnotationPresent(JointureDeColonne.class)) {
                        if (!field.isAnnotationPresent(Relation.class)) {
                        JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                        Object value = field.get(entity);
                        Field otherField = value.getClass().getDeclaredField(colonne.nom());
                        otherField.setAccessible(true);
                        statement.setObject(index++, otherField.get(value));
                    }

                    }else if (!field.getName().equalsIgnoreCase("id")) {
                        System.out.println(field.getName());
                        field.get(entity).getClass();
                        statement.setObject(index++, field.get(entity)); // Récupérer la valeur du champ
                    }
                    System.out.println(index);
                }

            }

            if (isJoiColumn){
                for (Field field : fields) {
                    field.setAccessible(true);
                    if(field.isAnnotationPresent(JointureDeColonne.class)) {
                        JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                        Object value = field.get(entity);
                        Field otherField = value.getClass().getDeclaredField(colonne.nom());
                        otherField.setAccessible(true);
                        statement.setObject(index, otherField.get(value));

                    }
                }
            }else {
                Field idField = clazz.getDeclaredField(idColumn);
                idField.setAccessible(true);
                statement.setObject(index, idField.get(entity));
            }


            statement.executeUpdate();
            statement.close();
            System.out.println("Record updated in table: " + tableName);
            return true;

        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Error updating record: " + e.getMessage(), e);
        }
    }
    default boolean delete(T entity) {
        try {
            Class<?> clazz = entity.getClass();
            String tableName = clazz.getSimpleName().toUpperCase();

            Field idField = null;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase("id")) {
                    idField = field;
                    break;
                }
            }

            if (idField == null) {
                throw new RuntimeException("L'entité doit avoir un champ 'id' pour identifier la clé primaire.");
            }

            idField.setAccessible(true);

            String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, idField.getName());

            PreparedStatement statement = connection.prepareStatement(sql);

            Object idValue = idField.get(entity);
            statement.setObject(1, idValue);

            statement.executeUpdate();
            statement.close();
            System.out.println("Record deleted from table: " + tableName);
            return true;

        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Error deleting record: " + e.getMessage(), e);
        }
    }
    default Optional<T> findById(Class<T> entity, PK id) {
        try {
            String tableName = entity.getSimpleName().toUpperCase();
            String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            Optional<?> opt = mappingResultSetObject(entity, rs);
            assert opt.orElse(null) != null;
            List<Object> objects = (List<Object>)opt.get();
            Optional<T> opt2 = (Optional<T>) Optional.of(objects.getFirst());
            return  opt2;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving record: " + e.getMessage(), e);
        }
    }
    default List<?> findAll(Class<T> entity) {
        try {
            String tableName = entity.getSimpleName().toUpperCase();
            String sql = String.format("SELECT * FROM %s", tableName);
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            return (List<?>) mappingResultSetObject(entity,rs).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    default boolean isARelationField(Field field) {
        if (field.isAnnotationPresent(Relation.class)) {
            Relation relation = field.getAnnotation(Relation.class);
            return relation.mappedBy().isEmpty();
        }
        return false;
    }
    default ResultSet executeQuery(String request) {
        try {
            PreparedStatement statement = connection.prepareStatement(request);
            return statement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    default Optional<?> mappingResultSetObject(Class<?> clazz,ResultSet resultSet) {
        List<Object> resultList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
//                    if (field.isAnnotationPresent(Relation.class))
//                    System.out.println(field.getName()+" : "+field.isAnnotationPresent(JointureDeColonne.class)+" : "+
//                            isARelationField(field)+" : "+field.isAnnotationPresent(Relation.class)+" : "+
//                            field.getAnnotation(Relation.class).mappedBy().isEmpty());
                    if (isARelationField(field) && field.isAnnotationPresent(JointureDeColonne.class)) {
                        JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                        Type genericType = field.getGenericType();
                            Class<?> clazz2 = (Class<?>) genericType;
                            String query = "SELECT * FROM " + clazz2.getSimpleName().toUpperCase()+" WHERE id = "+resultSet.getObject(colonne.nom());
                            ResultSet rs = executeQuery(query);
                            Object instance2 = mappingResultSetObject(clazz2, rs).orElse(null);
                            if (instance2 instanceof ArrayList<?> arrayList) {
                                field.setAccessible(true);
                                field.set(instance, arrayList.getFirst());
                            }

                    } else if (!field.isAnnotationPresent(Relation.class)) {
                        field.setAccessible(true);
                        Object value = resultSet.getObject(field.getName());
                        field.set(instance, value);
                    }
                }
                resultList.add(instance);
            }
        }catch (Exception _){
        }
        return Optional.of(resultList);
    }


    default boolean mappingObjectResultSet(Object instance) {
        try {
            Class<?> clazz = instance.getClass();
            String tableName = clazz.getSimpleName().toUpperCase();
            Field[] fields = clazz.getDeclaredFields();
            StringBuilder columns = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();

            for (Field field : fields) {
                if (isARelationField(field) && field.isAnnotationPresent(JointureDeColonne.class)){
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().toUpperCase().startsWith("GET"+field.getName().toUpperCase())) {
                            Object object = method.invoke(instance);
                            mappingObjectResultSet(object);
                        }
                    }
                        JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                        columns.append(colonne.nom()).append(",");
                        placeholders.append("?,");
                }
                else if (!field.isAnnotationPresent(Relation.class)) {
                    field.setAccessible(true);
                    columns.append(field.getName()).append(",");
                    placeholders.append("?,");
                }}

        // Supprimer la dernière virgule
            columns.deleteCharAt(columns.length() - 1);
            placeholders.deleteCharAt(placeholders.length() - 1);
            // Générer la requête SQL
            String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);

            // Injecter les valeurs dans la requête
            int index = 1;
            for (Field field : fields) {
                if (isARelationField(field) && field.isAnnotationPresent(JointureDeColonne.class)) {
                    field.setAccessible(true);
                    JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                    Object object = field.get(instance);
                    columns.append(colonne.nom()).append(",");
                    placeholders.append("?,");
                    Method[] methods = object.getClass().getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().toUpperCase().startsWith("GET"+colonne.nom().toUpperCase())) {
                            statement.setObject(index++, method.invoke(object)); // Récupérer la valeur du champ
                            System.out.println(field.getName() + " " + field.get(instance));
                        }
                    }
                }else
                if (!field.isAnnotationPresent(Relation.class)) {
                    field.setAccessible(true);
                    statement.setObject(index++, field.get(instance)); // Récupérer la valeur du champ
                    System.out.println(field.getName() + " " + field.get(instance));
                }

            }
            statement.executeUpdate();
            statement.close();
            System.out.println("Record created in table: " + tableName);
            return true;

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

