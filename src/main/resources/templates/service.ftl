<#--

Copyright (C) 2011-2017 Spring Agile

This file is part of Code Generator.

-->
/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.${className};
import com.agile.dao.interfaces.${className}Dao;
import com.agile.service.interfaces.${className}Service;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("${className?uncap_first}Service")
public class ${className}ServiceImpl extends AbstractDaoService<${className}> implements ${className}Service {

	public ${className}Dao ${className?uncap_first}Dao;

	@Autowired
	public ${className}ServiceImpl(IBaseDao<${className}> dao) {
		super(dao);
		this.${className?uncap_first}Dao = (${className}Dao)dao;
	}
}