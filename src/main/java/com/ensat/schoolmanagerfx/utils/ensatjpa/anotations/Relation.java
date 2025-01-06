package com.ensat.schoolmanagerfx.utils.ensatjpa.anotations;

import com.ensat.schoolmanagerfx.utils.ensatjpa.config.CascadeType;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.FetchType;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.OrphanRemovalType;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Relation {
    RelationType type();
    String mappedBy () default "";
    CascadeType cascade () default CascadeType.ALL;
    FetchType fetch () default FetchType.LAZY;
    OrphanRemovalType orphanRemoval () default OrphanRemovalType.DISABLED;
}
