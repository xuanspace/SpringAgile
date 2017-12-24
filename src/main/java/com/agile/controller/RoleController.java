/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.Role;
import com.agile.service.interfaces.RoleService;
import com.agile.validator.RoleValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/role")
public class RoleController extends AbstractDaoController<Role>{
	
	private RoleService roleService;
	private RoleValidator roleValidate;

	@Autowired
	public RoleController(RoleService service, RoleValidator validator) {
		super(service, validator);
		this.roleService = service;
		this.roleValidate = validator;
	}	
}
