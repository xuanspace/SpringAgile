/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import com.agile.framework.validate.AbstractValidator;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.agile.model.Menu;

@Repository("menuValidator")
public class MenuValidator extends AbstractValidator<Menu> {

    public MenuValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
    return Menu.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Menu menu = (Menu)target;
    }

    @Override
    public void constrain() {

    }
}