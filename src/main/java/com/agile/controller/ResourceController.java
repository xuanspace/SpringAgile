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

import com.agile.model.Resource;
import com.agile.service.interfaces.ResourceService;
import com.agile.validator.ResourceValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractDaoController<Resource>{
	
	private ResourceService resourceService;
	private ResourceValidator resourceValidate;

	@Autowired
	public ResourceController(ResourceService service, ResourceValidator validator) {
		super(service, validator);
		this.resourceService = service;
		this.resourceValidate = validator;
	}	
}
