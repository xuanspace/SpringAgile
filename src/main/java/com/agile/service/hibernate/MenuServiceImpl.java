/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.MenuDao;
import com.agile.model.Menu;
import com.agile.service.interfaces.MenuService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends AbstractDaoService<Menu> implements MenuService {

	public MenuDao menuDao;

	@Autowired
	public MenuServiceImpl(IBaseDao<Menu> dao) {
		super(dao);
		this.menuDao = (MenuDao)dao;
	}
}