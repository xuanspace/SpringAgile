<#--

Copyright (C) 2011-2015 Spring Agile

This file is part of Code Generator.

-->
/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import com.agile.common.BaseValidator;

import com.agile.model.${className};

@Repository("${className?uncap_first}Validator")
public class ${className}Validator extends BaseValidator<${className}> {
   
    @Override
    public boolean supports(Class<?> clazz) {
    	return ${className}.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ${className} ${className?uncap_first} = (${className})target;
    }

    @Override
    public void constrain() {

    }
}