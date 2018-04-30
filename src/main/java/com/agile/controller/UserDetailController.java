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
