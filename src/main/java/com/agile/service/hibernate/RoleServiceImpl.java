/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.RoleDao;
import com.agile.model.Role;
import com.agile.service.interfaces.RoleService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("roleService")
public class RoleServiceImpl extends AbstractDaoService<Role> implements RoleService {

	public RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(IBaseDao<Role> dao) {
		super(dao);
		this.roleDao = (RoleDao)dao;
	}
}