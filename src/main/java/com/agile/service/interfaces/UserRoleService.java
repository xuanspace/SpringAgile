/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.interfaces;

import com.agile.model.Role;
import com.agile.model.User;
import com.agile.model.UserRole;

import java.util.List;

import com.agile.framework.service.IDaoService;

public interface UserRoleService extends IDaoService<UserRole> {

    /**
     * 获取用户的角色
     * @param user 用户
     * @return 用户角色
     */
	public List<Role> getUserRoles(User user);
	
}
