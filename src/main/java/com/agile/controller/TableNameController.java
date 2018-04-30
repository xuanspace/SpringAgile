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

import com.agile.model.TableName;
import com.agile.service.interfaces.TableNameService;
import com.agile.validator.TableNameValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/tableName")
public class TableNameController extends AbstractDaoController<TableName>{
	
	private TableNameService tableNameService;
	private TableNameValidator tableNameValidate;

	@Autowired
	public TableNameController(TableNameService service, TableNameValidator validator) {
		super(service, validator);
		this.tableNameService = service;
		this.tableNameValidate = validator;
	}	
}
