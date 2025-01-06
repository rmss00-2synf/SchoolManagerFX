package com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.DatabaseConnection;

import java.lang.reflect.Field;
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
        try {
            Class<?> clazz = entity.getClass();
            String tableName = clazz.getSimpleName().toUpperCase();
            Field[] fields = clazz.getDeclaredFields();
            StringBuilder columns = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();
            for (Field field : fields) {
                if (field.isAnnotationPresent(JointureDeColonne.class)) {
                    JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                    columns.append(colonne.nom()).append(",");
                    placeholders.append("?,");
                } else if (!isARelationField(field)) {
                    field.setAccessible(true);
                    columns.append(field.getName()).append(",");
                    placeholders.append("?,");
                }
            }
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
                if (field.isAnnotationPresent(JointureDeColonne.class) || !isARelationField(field)) {
                    field.setAccessible(true);
                    statement.setObject(index++, field.get(entity)); // Récupérer la valeur du champ
                    System.out.println(field.getName() + " " + field.get(entity));
                }
            }
            statement.executeUpdate();
            statement.close();
            System.out.println("Record created in table: " + tableName);
            return true;

        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Error creating record: " + e.getMessage(), e);
        }
    }

    default boolean update(T entity) {
        try {
            Class<?> clazz = entity.getClass();
            String tableName = clazz.getSimpleName().toUpperCase();

            Field[] fields = clazz.getDeclaredFields();
            StringBuilder setClause = new StringBuilder();
            // Identifier la clé primaire (supposons qu'elle s'appelle "id")
            String idColumn = null;

            for (Field field : fields) {
                if (field.isAnnotationPresent(JointureDeColonne.class)) {
                    JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                    field.setAccessible(true);
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

            PreparedStatement statement = connection.prepareStatement(sql);

            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getName().equalsIgnoreCase("id")) {
                    statement.setObject(index++, field.get(entity)); // Récupérer la valeur du champ
                }
            }

            Field idField = clazz.getDeclaredField(idColumn);
            idField.setAccessible(true);
            statement.setObject(index, idField.get(entity));

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

    default Optional<T> getById(Class<T> entity, PK id) {
        try {
            String tableName = entity.getSimpleName().toUpperCase();

            T instance = (T) entity.getDeclaredConstructor().newInstance();

            String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                for (Field field : entity.getDeclaredFields()) {
                    if (field.isAnnotationPresent(JointureDeColonne.class)) {
                        JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                        field.setAccessible(true);
                        Object value = rs.getObject(colonne.nom());
                        field.set(instance, value);
                    } else if (!isARelationField(field)) {
                        field.setAccessible(true);
                        Object value = rs.getObject(field.getName());
                        field.set(instance, value);
                    }
                }
                return Optional.of(instance);
            }
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException("Error retrieving record: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    default List<T> getAll(Class<T> entity) {
        List<T> resultList = new ArrayList<>();
        try {
            String tableName = entity.getSimpleName().toUpperCase();
            String sql = String.format("SELECT * FROM %s", tableName);
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            return resultSetOfQuery(entity,rs).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Optional<List<T>> resultSetOfQuery(Class<T> entity, ResultSet resultSet)
    {

        List<T> resultList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T instance = entity.getDeclaredConstructor().newInstance();
                Field[] fields = entity.getDeclaredFields();
                System.out.println(fields.length);
                int i=0;
                for (Field field : fields) {
                    if (field.isAnnotationPresent(JointureDeColonne.class)) {
                        JointureDeColonne colonne = field.getAnnotation(JointureDeColonne.class);
                        field.setAccessible(true);
                        Object value = resultSet.getObject(colonne.nom());
                        field.set(instance, value);
                    } else if (!isARelationField(field)) {
                        field.setAccessible(true);
                        Object value = resultSet.getObject(field.getName());
                        field.set(instance, value);
                    }
                }
                System.out.println(instance);
                resultList.add(instance);
            }
        }catch (Exception _){
        }
        return Optional.of(resultList);
    }

    default boolean isARelationField(Field field) {
        if (field.isAnnotationPresent(Relation.class)) {
            Relation relation = field.getAnnotation(Relation.class);
            return !relation.mappedBy().isEmpty();
        }
        return false;
    }
}
