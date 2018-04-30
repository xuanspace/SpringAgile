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
import com.agile.model.User;
import com.agile.model.UserRole;
import com.agile.modules.database.DB;
import com.agile.modules.database.SYS_ROLE;
import com.agile.modules.database.SYS_USER;
import com.agile.modules.database.SYS_USER_ROLE;
import com.agile.dao.interfaces.UserRoleDao;
import com.agile.service.interfaces.UserRoleService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("userRoleService")
public class UserRoleServiceImpl extends AbstractDaoService<UserRole> implements UserRoleService {

	public UserRoleDao userRoleDao;

	@Autowired
	public UserRoleServiceImpl(IBaseDao<UserRole> dao) {
		super(dao);
		this.userRoleDao = (UserRoleDao)dao;
	}

    /**
     * 获取用户的角色
     * @param name 用户名
     * @return 用户实例
     */	
	@Override
	public List<Role> getUserRoles(User user) {
		Builder query = userRoleDao.queryBuilder();
		query.select(DB.SYS_ROLE);
		query.join(SYS_USER_ROLE.ROLE_ID).on(SYS_ROLE.ID);
		query.where(SYS_USER_ROLE.USER_ID.eq(user.getId()));
		query.setResultClass(Role.class);
		return (List<Role>)query.list();
	}
}