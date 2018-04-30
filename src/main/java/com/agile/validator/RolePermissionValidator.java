/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import com.agile.common.BaseValidator;

import com.agile.model.RolePermission;

@Repository("rolePermissionValidator")
public class RolePermissionValidator extends BaseValidator<RolePermission> {
   
    @Override
    public boolean supports(Class<?> clazz) {
    	return RolePermission.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RolePermission rolePermission = (RolePermission)target;
    }

    @Override
    public void constrain() {

    }
}