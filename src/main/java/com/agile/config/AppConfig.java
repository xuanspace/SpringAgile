/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
 
package com.agile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:spring-mvc.xml", "spring-hibernate.xml", "spring-database.xml"})
public class AppConfig {
	
    @Bean  
    public String message() {  
        return "hello";  
    }  	

}
