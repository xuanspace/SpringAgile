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

import com.agile.model.UserGroup;
import com.agile.service.interfaces.UserGroupService;
import com.agile.validator.UserGroupValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/userGroup")
public class UserGroupController extends AbstractDaoController<UserGroup>{
	
	private UserGroupService userGroupService;
	private UserGroupValidator userGroupValidate;

	@Autowired
	public UserGroupController(UserGroupService service, UserGroupValidator validator) {
		super(service, validator);
		this.userGroupService = service;
		this.userGroupValidate = validator;
	}	
}
