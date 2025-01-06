package com.ensat.schoolmanagerfx.utils.ensatjpa.config;

record DatabaseConfig(
        String DRIVER,
        String URL,
        String USERNAME,
        String PASSWORD) { }