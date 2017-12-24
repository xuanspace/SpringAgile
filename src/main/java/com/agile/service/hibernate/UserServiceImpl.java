/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.UserDao;
import com.agile.model.User;
import com.agile.service.interfaces.UserService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userService")
public class UserServiceImpl extends AbstractDaoService<User> implements UserService {

	public UserDao userDao;

	@Autowired
	public UserServiceImpl(IBaseDao<User> dao) {
		super(dao);
		this.userDao = (UserDao)dao;
	}
}