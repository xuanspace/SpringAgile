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

import com.agile.model.Group;
import com.agile.service.interfaces.GroupService;
import com.agile.validator.GroupValidator;
import com.agile.framework.controller.AbstractDaoController;

@Controller
@RequestMapping("/group")
public class GroupController extends AbstractDaoController<Group>{
	
	private GroupService groupService;
	private GroupValidator groupValidate;

	@Autowired
	public GroupController(GroupService service, GroupValidator validator) {
		super(service, validator);
		this.groupService = service;
		this.groupValidate = validator;
	}	
}
