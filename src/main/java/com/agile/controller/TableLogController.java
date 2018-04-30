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

import com.agile.model.TableLog;
import com.agile.service.interfaces.TableLogService;
import com.agile.validator.TableLogValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/tableLog")
public class TableLogController extends AbstractDaoController<TableLog>{
	
	private TableLogService tableLogService;
	private TableLogValidator tableLogValidate;

	@Autowired
	public TableLogController(TableLogService service, TableLogValidator validator) {
		super(service, validator);
		this.tableLogService = service;
		this.tableLogValidate = validator;
	}	
}
