/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.UserRole;
import com.agile.service.interfaces.UserRoleService;
import com.agile.validator.UserRoleValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/userRole")
public class UserRoleController extends AbstractDaoController<UserRole>{
	
	private UserRoleService userRoleService;
	private UserRoleValidator userRoleValidate;

	@Autowired
	public UserRoleController(UserRoleService service, UserRoleValidator validator) {
		super(service, validator);
		this.userRoleService = service;
		this.userRoleValidate = validator;
	}	
}
