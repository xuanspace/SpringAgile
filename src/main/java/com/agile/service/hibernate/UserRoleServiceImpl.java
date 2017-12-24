/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.UserRoleDao;
import com.agile.model.UserRole;
import com.agile.service.interfaces.UserRoleService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userRoleService")
public class UserRoleServiceImpl extends AbstractDaoService<UserRole> implements UserRoleService {

	public UserRoleDao userRoleDao;

	@Autowired
	public UserRoleServiceImpl(IBaseDao<UserRole> dao) {
		super(dao);
		this.userRoleDao = (UserRoleDao)dao;
	}
}