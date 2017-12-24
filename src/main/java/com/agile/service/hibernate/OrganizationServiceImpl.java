/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.OrganizationDao;
import com.agile.model.Organization;
import com.agile.service.interfaces.OrganizationService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("organizationService")
public class OrganizationServiceImpl extends AbstractDaoService<Organization> implements OrganizationService {

	public OrganizationDao organizationDao;

	@Autowired
	public OrganizationServiceImpl(IBaseDao<Organization> dao) {
		super(dao);
		this.organizationDao = (OrganizationDao)dao;
	}
}