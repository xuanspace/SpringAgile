/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.AreaDao;
import com.agile.model.Area;
import com.agile.service.interfaces.AreaService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("areaService")
public class AreaServiceImpl extends AbstractDaoService<Area> implements AreaService {

	public AreaDao areaDao;

	@Autowired
	public AreaServiceImpl(IBaseDao<Area> dao) {
		super(dao);
		this.areaDao = (AreaDao)dao;
	}
}