/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.Area;
import com.agile.service.interfaces.AreaService;
import com.agile.validator.AreaValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/area")
public class AreaController extends AbstractDaoController<Area>{
	
	private AreaService areaService;
	private AreaValidator areaValidate;

	@Autowired
	public AreaController(AreaService service, AreaValidator validator) {
		super(service, validator);
		this.areaService = service;
		this.areaValidate = validator;
	}	
}
