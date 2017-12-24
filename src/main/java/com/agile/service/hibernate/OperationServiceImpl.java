/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.OperationDao;
import com.agile.model.Operation;
import com.agile.service.interfaces.OperationService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("operationService")
public class OperationServiceImpl extends AbstractDaoService<Operation> implements OperationService {

	public OperationDao operationDao;

	@Autowired
	public OperationServiceImpl(IBaseDao<Operation> dao) {
		super(dao);
		this.operationDao = (OperationDao)dao;
	}
}