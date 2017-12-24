/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import com.agile.framework.validate.AbstractValidator;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.agile.model.UserGroup;

@Repository("userGroupValidator")
public class UserGroupValidator extends AbstractValidator<UserGroup> {

    public UserGroupValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
    return UserGroup.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserGroup userGroup = (UserGroup)target;
    }

    @Override
    public void constrain() {

    }
}