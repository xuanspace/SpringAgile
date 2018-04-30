/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.interfaces;

import com.agile.model.Role;
import com.agile.framework.service.IDaoService;

public interface RoleService extends IDaoService<Role> {

    /**
     * 根据角色名户名获取角色
     * @param name 角色名
     * @return 角色实例
     */
	public Role getRoleByName(String name);
}
