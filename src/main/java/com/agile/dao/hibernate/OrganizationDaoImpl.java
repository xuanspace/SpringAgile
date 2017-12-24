/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.agile.framework.persistence.AbstractHibernateDao;
import com.agile.dao.interfaces.OrganizationDao;
import com.agile.model.Organization;

@Repository("organizationDao")
public class OrganizationDaoImpl extends AbstractHibernateDao<Organization> implements OrganizationDao {

	public OrganizationDaoImpl() {
	}

}
