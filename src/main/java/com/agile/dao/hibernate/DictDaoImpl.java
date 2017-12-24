/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.agile.framework.persistence.AbstractHibernateDao;
import com.agile.dao.interfaces.DictDao;
import com.agile.model.Dict;

@Repository("dictDao")
public class DictDaoImpl extends AbstractHibernateDao<Dict> implements DictDao {

	public DictDaoImpl() {
	}

}
