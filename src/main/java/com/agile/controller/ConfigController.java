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

import com.agile.model.Config;
import com.agile.service.interfaces.ConfigService;
import com.agile.validator.ConfigValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/config")
public class ConfigController extends AbstractDaoController<Config>{
	
	private ConfigService configService;
	private ConfigValidator configValidate;

	@Autowired
	public ConfigController(ConfigService service, ConfigValidator validator) {
		super(service, validator);
		this.configService = service;
		this.configValidate = validator;
	}	
}
