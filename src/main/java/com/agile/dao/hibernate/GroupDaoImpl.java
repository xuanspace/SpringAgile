/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.agile.framework.persistence.AbstractHibernateDao;
import com.agile.dao.interfaces.GroupDao;
import com.agile.model.Group;

@Repository("groupDao")
public class GroupDaoImpl extends AbstractHibernateDao<Group> implements GroupDao {

	public GroupDaoImpl() {
	}

}
