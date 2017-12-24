/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.ModuleDao;
import com.agile.model.Module;
import com.agile.service.interfaces.ModuleService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("moduleService")
public class ModuleServiceImpl extends AbstractDaoService<Module> implements ModuleService {

	public ModuleDao moduleDao;

	@Autowired
	public ModuleServiceImpl(IBaseDao<Module> dao) {
		super(dao);
		this.moduleDao = (ModuleDao)dao;
	}
}