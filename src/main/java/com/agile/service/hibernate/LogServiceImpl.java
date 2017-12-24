/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.LogDao;
import com.agile.model.Log;
import com.agile.service.interfaces.LogService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("logService")
public class LogServiceImpl extends AbstractDaoService<Log> implements LogService {

	public LogDao logDao;

	@Autowired
	public LogServiceImpl(IBaseDao<Log> dao) {
		super(dao);
		this.logDao = (LogDao)dao;
	}
}