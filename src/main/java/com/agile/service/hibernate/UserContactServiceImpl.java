/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.UserContactDao;
import com.agile.model.UserContact;
import com.agile.service.interfaces.UserContactService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userContactService")
public class UserContactServiceImpl extends AbstractDaoService<UserContact> implements UserContactService {

	public UserContactDao userContactDao;

	@Autowired
	public UserContactServiceImpl(IBaseDao<UserContact> dao) {
		super(dao);
		this.userContactDao = (UserContactDao)dao;
	}
}