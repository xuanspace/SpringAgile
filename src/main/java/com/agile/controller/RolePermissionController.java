/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agile.model.RolePermission;
import com.agile.service.interfaces.RolePermissionService;
import com.agile.validator.RolePermissionValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/rolePermission")
public class RolePermissionController extends AbstractDaoController<RolePermission>{
	
	private RolePermissionService rolePermissionService;
	private RolePermissionValidator rolePermissionValidate;

	@Autowired
	public RolePermissionController(RolePermissionService service, RolePermissionValidator validator) {
		super(service, validator);
		this.rolePermissionService = service;
		this.rolePermissionValidate = validator;
	}	
}
