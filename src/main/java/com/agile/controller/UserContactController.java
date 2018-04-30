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

import com.agile.model.UserContact;
import com.agile.service.interfaces.UserContactService;
import com.agile.validator.UserContactValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/userContact")
public class UserContactController extends AbstractDaoController<UserContact>{
	
	private UserContactService userContactService;
	private UserContactValidator userContactValidate;

	@Autowired
	public UserContactController(UserContactService service, UserContactValidator validator) {
		super(service, validator);
		this.userContactService = service;
		this.userContactValidate = validator;
	}	
}
