<#--

Copyright (C) 2011-2015 Spring Agile

This file is part of Code Generator.

-->
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

import com.agile.model.${className};
import com.agile.service.interfaces.${className}Service;
import com.agile.validator.${className}Validator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/${className?uncap_first}")
public class ${className}Controller extends AbstractDaoController<${className}>{
	
	private ${className}Service ${className?uncap_first}Service;
	private ${className}Validator ${className?uncap_first}Validate;

	@Autowired
	public ${className}Controller(${className}Service service, ${className}Validator validator) {
		super(service, validator);
		this.${className?uncap_first}Service = service;
		this.${className?uncap_first}Validate = validator;
	}	
}
