package com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ENSATJPA <T,PK> extends CRUD<T,PK>{
    default Optional<List<T>> findByAttribute(Class<T> entity, String query) {
        System.out.println("findByAttribute ========================== ALYY ALLY");
        System.out.println("attribute: " + query);
        List<T> resultList = new ArrayList<>();
        try {
            System.out.println(query);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            return resultSetOfQuery(entity,rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving records: " + e.getMessage(), e);
        }
    }
}
