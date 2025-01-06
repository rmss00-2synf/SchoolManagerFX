package com.ensat.schoolmanagerfx.utils.ensatjpa.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Requete {
    String value() default "";
    String param() default "";
    }
