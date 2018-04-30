/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.TableName;
import com.agile.dao.interfaces.TableNameDao;
import com.agile.service.interfaces.TableNameService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("tableNameService")
public class TableNameServiceImpl extends AbstractDaoService<TableName> implements TableNameService {

	public TableNameDao tableNameDao;

	@Autowired
	public TableNameServiceImpl(IBaseDao<TableName> dao) {
		super(dao);
		this.tableNameDao = (TableNameDao)dao;
	}
}