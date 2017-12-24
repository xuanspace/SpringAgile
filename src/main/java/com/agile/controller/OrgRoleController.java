/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.OrgRole;
import com.agile.service.interfaces.OrgRoleService;
import com.agile.validator.OrgRoleValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/orgRole")
public class OrgRoleController extends AbstractDaoController<OrgRole>{
	
	private OrgRoleService orgRoleService;
	private OrgRoleValidator orgRoleValidate;

	@Autowired
	public OrgRoleController(OrgRoleService service, OrgRoleValidator validator) {
		super(service, validator);
		this.orgRoleService = service;
		this.orgRoleValidate = validator;
	}	
}
