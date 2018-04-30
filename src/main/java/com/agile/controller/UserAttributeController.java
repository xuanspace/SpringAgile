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

import com.agile.model.UserAttribute;
import com.agile.service.interfaces.UserAttributeService;
import com.agile.validator.UserAttributeValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/userAttribute")
public class UserAttributeController extends AbstractDaoController<UserAttribute>{
	
	private UserAttributeService userAttributeService;
	private UserAttributeValidator userAttributeValidate;

	@Autowired
	public UserAttributeController(UserAttributeService service, UserAttributeValidator validator) {
		super(service, validator);
		this.userAttributeService = service;
		this.userAttributeValidate = validator;
	}	
}
