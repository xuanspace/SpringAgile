/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import com.agile.framework.validate.AbstractValidator;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.agile.model.Log;

@Repository("logValidator")
public class LogValidator extends AbstractValidator<Log> {

    public LogValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
    return Log.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Log log = (Log)target;
    }

    @Override
    public void constrain() {

    }
}