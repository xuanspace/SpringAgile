/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.agile.framework.persistence.AbstractHibernateDao;
import com.agile.dao.interfaces.OrgRoleDao;
import com.agile.model.OrgRole;

@Repository("orgRoleDao")
public class OrgRoleDaoImpl extends AbstractHibernateDao<OrgRole> implements OrgRoleDao {

	public OrgRoleDaoImpl() {
	}

}
