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

import com.agile.model.Menu;
import com.agile.service.interfaces.MenuService;
import com.agile.validator.MenuValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractDaoController<Menu>{
	
	private MenuService menuService;
	private MenuValidator menuValidate;

	@Autowired
	public MenuController(MenuService service, MenuValidator validator) {
		super(service, validator);
		this.menuService = service;
		this.menuValidate = validator;
	}	
}
