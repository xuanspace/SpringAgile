/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import com.agile.framework.validate.AbstractValidator;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.agile.model.Operation;

@Repository("operationValidator")
public class OperationValidator extends AbstractValidator<Operation> {

    public OperationValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
    return Operation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Operation operation = (Operation)target;
    }

    @Override
    public void constrain() {

    }
}