/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.interfaces;

import com.agile.model.User;
import com.agile.framework.service.IDaoService;

public interface UserService extends IDaoService<User> {

    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return 用户实例
     */
	public User getUserByName(String name);
	
}
