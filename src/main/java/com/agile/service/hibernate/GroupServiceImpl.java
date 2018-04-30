/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.Group;
import com.agile.dao.interfaces.GroupDao;
import com.agile.service.interfaces.GroupService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("groupService")
public class GroupServiceImpl extends AbstractDaoService<Group> implements GroupService {

	public GroupDao groupDao;

	@Autowired
	public GroupServiceImpl(IBaseDao<Group> dao) {
		super(dao);
		this.groupDao = (GroupDao)dao;
	}
}