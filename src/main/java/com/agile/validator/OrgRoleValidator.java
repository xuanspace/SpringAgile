/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.validator;

import com.agile.framework.validate.AbstractValidator;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.agile.model.OrgRole;

@Repository("orgRoleValidator")
public class OrgRoleValidator extends AbstractValidator<OrgRole> {

    public OrgRoleValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
    return OrgRole.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrgRole orgRole = (OrgRole)target;
    }

    @Override
    public void constrain() {

    }
}