/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.interfaces;

import com.agile.model.Resource;
import com.agile.framework.service.IDaoService;

public interface ResourceService extends IDaoService<Resource> {

    /**
     * 根据资源名获取资源实例
     * @param name 资源名
     * @return 资源实例
     */
	public Resource getResourceByName(String name);
}
