/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.Permission;
import com.agile.dao.interfaces.PermissionDao;
import com.agile.service.interfaces.PermissionService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("permissionService")
public class PermissionServiceImpl extends AbstractDaoService<Permission> implements PermissionService {

	public PermissionDao permissionDao;

	@Autowired
	public PermissionServiceImpl(IBaseDao<Permission> dao) {
		super(dao);
		this.permissionDao = (PermissionDao)dao;
	}
}