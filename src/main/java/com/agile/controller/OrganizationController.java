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
