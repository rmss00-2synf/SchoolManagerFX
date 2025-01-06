package com.ensat.schoolmanagerfx.utils.ensatjpa.config;

public enum RelationType {
    ONE_TO_ONE,        // Relation One-to-One (1:1)
    ONE_TO_MANY,       // Relation One-to-Many (1:N)
    MANY_TO_ONE,       // Relation Many-to-One (N:1)
    MANY_TO_MANY,// Relation Many-to-Many (N:N)
    ANY;
}

