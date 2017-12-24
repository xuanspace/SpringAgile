/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.UserDetail;
import com.agile.service.interfaces.UserDetailService;
import com.agile.validator.UserDetailValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/userDetail")
public class UserDetailController extends AbstractDaoController<UserDetail>{
	
	private UserDetailService userDetailService;
	private UserDetailValidator userDetailValidate;

	@Autowired
	public UserDetailController(UserDetailService service, UserDetailValidator validator) {
		super(service, validator);
		this.userDetailService = service;
		this.userDetailValidate = validator;
	}	
}
