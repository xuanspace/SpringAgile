<#--

Copyright (C) 2011-2015 Spring Agile

This file is part of Code Generator.

-->
/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.agile.framework.persistence.AbstractHibernateDao;
import com.agile.dao.interfaces.${className}Dao;
import com.agile.model.${className};

@Repository("${className?uncap_first}Dao")
public class ${className}DaoImpl extends AbstractHibernateDao<${className}> implements ${className}Dao {

	public ${className}DaoImpl() {
	}

}
