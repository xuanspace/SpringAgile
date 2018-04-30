/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.User;
import com.agile.modules.database.SYS_USER;
import com.agile.dao.interfaces.UserDao;
import com.agile.service.interfaces.UserService;


import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;
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

    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return 用户实例
     */
	@Override
	public User getUserByName(String name) {
		Builder query = userDao.queryBuilder();
		query.select(SYS_USER.NAME.eq(name));		
		List<User> data = userDao.getList(query);
		if (data != null && data.size() > 0)
			return data.get(0);
		return null;
	}
	
}