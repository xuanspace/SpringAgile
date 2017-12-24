/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.OrgRoleDao;
import com.agile.model.OrgRole;
import com.agile.service.interfaces.OrgRoleService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("orgRoleService")
public class OrgRoleServiceImpl extends AbstractDaoService<OrgRole> implements OrgRoleService {

	public OrgRoleDao orgRoleDao;

	@Autowired
	public OrgRoleServiceImpl(IBaseDao<OrgRole> dao) {
		super(dao);
		this.orgRoleDao = (OrgRoleDao)dao;
	}
}