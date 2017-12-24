/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.model.Dict;
import com.agile.service.interfaces.DictService;
import com.agile.validator.DictValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/dict")
public class DictController extends AbstractDaoController<Dict>{
	
	private DictService dictService;
	private DictValidator dictValidate;

	@Autowired
	public DictController(DictService service, DictValidator validator) {
		super(service, validator);
		this.dictService = service;
		this.dictValidate = validator;
	}	
}
