/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.Organization;
import com.agile.service.interfaces.OrganizationService;
import com.agile.validator.OrganizationValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends AbstractDaoController<Organization>{
	
	private OrganizationService organizationService;
	private OrganizationValidator organizationValidate;

	@Autowired
	public OrganizationController(OrganizationService service, OrganizationValidator validator) {
		super(service, validator);
		this.organizationService = service;
		this.organizationValidate = validator;
	}	
}
