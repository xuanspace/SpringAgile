/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.Config;
import com.agile.dao.interfaces.ConfigDao;
import com.agile.service.interfaces.ConfigService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("configService")
public class ConfigServiceImpl extends AbstractDaoService<Config> implements ConfigService {

	public ConfigDao configDao;

	@Autowired
	public ConfigServiceImpl(IBaseDao<Config> dao) {
		super(dao);
		this.configDao = (ConfigDao)dao;
	}
}