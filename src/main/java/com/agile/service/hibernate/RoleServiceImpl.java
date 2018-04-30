/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.Role;
import com.agile.modules.database.SYS_ROLE;
import com.agile.dao.interfaces.RoleDao;
import com.agile.service.interfaces.RoleService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("roleService")
public class RoleServiceImpl extends AbstractDaoService<Role> implements RoleService {

	public RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(IBaseDao<Role> dao) {
		super(dao);
		this.roleDao = (RoleDao)dao;
	}
	
    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return 用户实例
     */
	@Override
	public Role getRoleByName(String name) {
		Builder query = roleDao.queryBuilder();
		query.select(SYS_ROLE.NAME.eq(name));		
		List<Role> data = roleDao.getList(query);
		if (data != null && data.size() > 0)
			return data.get(0);
		return null;
	}	
}