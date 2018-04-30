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

import com.agile.model.Operation;
import com.agile.service.interfaces.OperationService;
import com.agile.validator.OperationValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/operation")
public class OperationController extends AbstractDaoController<Operation>{
	
	private OperationService operationService;
	private OperationValidator operationValidate;

	@Autowired
	public OperationController(OperationService service, OperationValidator validator) {
		super(service, validator);
		this.operationService = service;
		this.operationValidate = validator;
	}	
}
