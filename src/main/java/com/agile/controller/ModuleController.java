/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.Module;
import com.agile.service.interfaces.ModuleService;
import com.agile.validator.ModuleValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/module")
public class ModuleController extends AbstractDaoController<Module>{
	
	private ModuleService moduleService;
	private ModuleValidator moduleValidate;

	@Autowired
	public ModuleController(ModuleService service, ModuleValidator validator) {
		super(service, validator);
		this.moduleService = service;
		this.moduleValidate = validator;
	}	
}
