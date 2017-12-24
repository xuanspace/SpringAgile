/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.DictDao;
import com.agile.model.Dict;
import com.agile.service.interfaces.DictService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("dictService")
public class DictServiceImpl extends AbstractDaoService<Dict> implements DictService {

	public DictDao dictDao;

	@Autowired
	public DictServiceImpl(IBaseDao<Dict> dao) {
		super(dao);
		this.dictDao = (DictDao)dao;
	}
}