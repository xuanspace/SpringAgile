/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.RoleMenuDao;
import com.agile.model.RoleMenu;
import com.agile.service.interfaces.RoleMenuService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("roleMenuService")
public class RoleMenuServiceImpl extends AbstractDaoService<RoleMenu> implements RoleMenuService {

	public RoleMenuDao roleMenuDao;

	@Autowired
	public RoleMenuServiceImpl(IBaseDao<RoleMenu> dao) {
		super(dao);
		this.roleMenuDao = (RoleMenuDao)dao;
	}
}