/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.RoleMenu;
import com.agile.service.interfaces.RoleMenuService;
import com.agile.validator.RoleMenuValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/roleMenu")
public class RoleMenuController extends AbstractDaoController<RoleMenu>{
	
	private RoleMenuService roleMenuService;
	private RoleMenuValidator roleMenuValidate;

	@Autowired
	public RoleMenuController(RoleMenuService service, RoleMenuValidator validator) {
		super(service, validator);
		this.roleMenuService = service;
		this.roleMenuValidate = validator;
	}	
}
