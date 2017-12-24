/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import com.agile.framework.validate.AbstractValidator;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.agile.model.Permission;

@Repository("permissionValidator")
public class PermissionValidator extends AbstractValidator<Permission> {

    public PermissionValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
    return Permission.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Permission permission = (Permission)target;
    }

    @Override
    public void constrain() {

    }
}