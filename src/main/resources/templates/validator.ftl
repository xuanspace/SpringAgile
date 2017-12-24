<#--

Copyright (C) 2011-2015 Spring Agile

This file is part of Code Generator.

-->
/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import com.agile.framework.validate.AbstractValidator;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.agile.entity.${className};

@Repository("${className?uncap_first}Validator")
public class ${className}Validator extends AbstractValidator<${className}> {

    public ${className}Validator() {
    }

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