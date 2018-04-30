/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.interfaces;

import com.agile.model.Role;
import com.agile.model.RolePermission;

import java.util.List;

import com.agile.framework.service.IDaoService;

public interface RolePermissionService extends IDaoService<RolePermission> {

    /**
     * 获取角色的权限
     * @param role 角色
     * @return 角色的权限
     */
	public List<RolePermission> getRolePermissions(Role role);
}
