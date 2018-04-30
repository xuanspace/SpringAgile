/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import com.agile.common.BaseValidator;

import com.agile.model.Role;

@Repository("roleValidator")
public class RoleValidator extends BaseValidator<Role> {
   
    @Override
    public boolean supports(Class<?> clazz) {
    	return Role.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Role role = (Role)target;
    }

    @Override
    public void constrain() {

    }
}