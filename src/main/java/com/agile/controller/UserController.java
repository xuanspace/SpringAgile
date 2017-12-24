/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.User;
import com.agile.service.interfaces.UserService;
import com.agile.validator.UserValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractDaoController<User>{
	
	private UserService userService;
	private UserValidator userValidate;

	@Autowired
	public UserController(UserService service, UserValidator validator) {
		super(service, validator);
		this.userService = service;
		this.userValidate = validator;
	}
}
