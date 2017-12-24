/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.UserOrgDao;
import com.agile.model.UserOrg;
import com.agile.service.interfaces.UserOrgService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userOrgService")
public class UserOrgServiceImpl extends AbstractDaoService<UserOrg> implements UserOrgService {

	public UserOrgDao userOrgDao;

	@Autowired
	public UserOrgServiceImpl(IBaseDao<UserOrg> dao) {
		super(dao);
		this.userOrgDao = (UserOrgDao)dao;
	}
}