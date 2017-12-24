/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.UserGroupDao;
import com.agile.model.UserGroup;
import com.agile.service.interfaces.UserGroupService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userGroupService")
public class UserGroupServiceImpl extends AbstractDaoService<UserGroup> implements UserGroupService {

	public UserGroupDao userGroupDao;

	@Autowired
	public UserGroupServiceImpl(IBaseDao<UserGroup> dao) {
		super(dao);
		this.userGroupDao = (UserGroupDao)dao;
	}
}