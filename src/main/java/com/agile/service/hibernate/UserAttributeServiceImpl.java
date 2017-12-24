/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.UserAttributeDao;
import com.agile.model.UserAttribute;
import com.agile.service.interfaces.UserAttributeService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userAttributeService")
public class UserAttributeServiceImpl extends AbstractDaoService<UserAttribute> implements UserAttributeService {

	public UserAttributeDao userAttributeDao;

	@Autowired
	public UserAttributeServiceImpl(IBaseDao<UserAttribute> dao) {
		super(dao);
		this.userAttributeDao = (UserAttributeDao)dao;
	}
}