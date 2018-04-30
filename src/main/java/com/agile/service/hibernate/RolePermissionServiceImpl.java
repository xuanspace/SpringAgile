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
import com.agile.model.RolePermission;
import com.agile.modules.database.SYS_ROLE_PERMISSION;
import com.agile.dao.interfaces.RolePermissionDao;
import com.agile.service.interfaces.RolePermissionService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends AbstractDaoService<RolePermission> implements RolePermissionService {

	public RolePermissionDao rolePermissionDao;

	@Autowired
	public RolePermissionServiceImpl(IBaseDao<RolePermission> dao) {
		super(dao);
		this.rolePermissionDao = (RolePermissionDao)dao;
	}
	
    /**
     * 获取角色的权限
     * @param role 角色
     * @return 角色的权限
     */
	@Override
	public List<RolePermission> getRolePermissions(Role role) {
		Builder query = rolePermissionDao.queryBuilder();
		query.select(SYS_ROLE_PERMISSION.ROLE_ID.eq(role.getId()));
		return rolePermissionDao.getList(query);
	}	
	
}