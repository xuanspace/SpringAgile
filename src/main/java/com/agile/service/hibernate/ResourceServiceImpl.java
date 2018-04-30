/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.Resource;
import com.agile.modules.database.SYS_RESOURCE;
import com.agile.dao.interfaces.ResourceDao;
import com.agile.service.interfaces.ResourceService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;
import com.agile.framework.service.AbstractDaoService;

@Transactional
@Service("resourceService")
public class ResourceServiceImpl extends AbstractDaoService<Resource> implements ResourceService {

	public ResourceDao resourceDao;

	@Autowired
	public ResourceServiceImpl(IBaseDao<Resource> dao) {
		super(dao);
		this.resourceDao = (ResourceDao)dao;
	}

    /**
     * 根据资源名获取资源实例
     * @param name 资源名
     * @return 资源实例
     */
	@Override
	public Resource getResourceByName(String name) {
		Builder query = resourceDao.queryBuilder();
		query.select(SYS_RESOURCE.NAME.eq(name));
		List<Resource> data = resourceDao.getList(query);
		if (data != null && data.size() > 0)
			return data.get(0);
		return null;
	}
	
	
}