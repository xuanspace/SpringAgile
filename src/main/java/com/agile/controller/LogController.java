/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.Log;
import com.agile.service.interfaces.LogService;
import com.agile.validator.LogValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/log")
public class LogController extends AbstractDaoController<Log>{
	
	private LogService logService;
	private LogValidator logValidate;

	@Autowired
	public LogController(LogService service, LogValidator validator) {
		super(service, validator);
		this.logService = service;
		this.logValidate = validator;
	}	
}
