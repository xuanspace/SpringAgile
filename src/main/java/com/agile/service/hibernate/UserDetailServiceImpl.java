/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.UserDetailDao;
import com.agile.model.UserDetail;
import com.agile.service.interfaces.UserDetailService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userDetailService")
public class UserDetailServiceImpl extends AbstractDaoService<UserDetail> implements UserDetailService {

	public UserDetailDao userDetailDao;

	@Autowired
	public UserDetailServiceImpl(IBaseDao<UserDetail> dao) {
		super(dao);
		this.userDetailDao = (UserDetailDao)dao;
	}
}