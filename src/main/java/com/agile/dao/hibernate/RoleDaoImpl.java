/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.agile.framework.persistence.AbstractHibernateDao;
import com.agile.dao.interfaces.RoleDao;
import com.agile.model.Role;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractHibernateDao<Role> implements RoleDao {

	public RoleDaoImpl() {
	}

}
