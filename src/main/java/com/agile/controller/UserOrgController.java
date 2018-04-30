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

import com.agile.model.UserOrg;
import com.agile.service.interfaces.UserOrgService;
import com.agile.validator.UserOrgValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/userOrg")
public class UserOrgController extends AbstractDaoController<UserOrg>{
	
	private UserOrgService userOrgService;
	private UserOrgValidator userOrgValidate;

	@Autowired
	public UserOrgController(UserOrgService service, UserOrgValidator validator) {
		super(service, validator);
		this.userOrgService = service;
		this.userOrgValidate = validator;
	}	
}
