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

import com.agile.model.Permission;
import com.agile.service.interfaces.PermissionService;
import com.agile.validator.PermissionValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/permission")
public class PermissionController extends AbstractDaoController<Permission>{
	
	private PermissionService permissionService;
	private PermissionValidator permissionValidate;

	@Autowired
	public PermissionController(PermissionService service, PermissionValidator validator) {
		super(service, validator);
		this.permissionService = service;
		this.permissionValidate = validator;
	}	
}
