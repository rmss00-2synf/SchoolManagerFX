package com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ENSATJPA <T,PK> extends CRUD<T,PK>{
    default Optional<?> findByQuery(String query,Class<T> entity) {
        System.out.println("findByAttribute ========================== ALYY ALLY");
        System.out.println("attribute: " + query);
        try {
            System.out.println(query);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            return mappingResultSetObject(entity,rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving records: " + e.getMessage(), e);
        }
    }
}
