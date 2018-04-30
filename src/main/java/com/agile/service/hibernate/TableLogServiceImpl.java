/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.TableLog;
import com.agile.dao.interfaces.TableLogDao;
import com.agile.service.interfaces.TableLogService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("tableLogService")
public class TableLogServiceImpl extends AbstractDaoService<TableLog> implements TableLogService {

	public TableLogDao tableLogDao;

	@Autowired
	public TableLogServiceImpl(IBaseDao<TableLog> dao) {
		super(dao);
		this.tableLogDao = (TableLogDao)dao;
	}
}