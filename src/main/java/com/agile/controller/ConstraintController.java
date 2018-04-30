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

import com.agile.model.Constraint;
import com.agile.service.interfaces.ConstraintService;
import com.agile.validator.ConstraintValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/constraint")
public class ConstraintController extends AbstractDaoController<Constraint>{
	
	private ConstraintService constraintService;
	private ConstraintValidator constraintValidate;

	@Autowired
	public ConstraintController(ConstraintService service, ConstraintValidator validator) {
		super(service, validator);
		this.constraintService = service;
		this.constraintValidate = validator;
	}	
}
