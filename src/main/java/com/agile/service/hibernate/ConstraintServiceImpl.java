/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.Constraint;
import com.agile.dao.interfaces.ConstraintDao;
import com.agile.service.interfaces.ConstraintService;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.service.AbstractDaoService;
import com.agile.framework.utils.EntityUtils;
import com.agile.framework.utils.StringUtils;
import com.agile.framework.validate.FieldConstraint;

@Transactional
@Service("constraintService")
public class ConstraintServiceImpl extends AbstractDaoService<Constraint> implements ConstraintService {

	public ConstraintDao constraintDao;
	private List<Constraint> constraints; 

	@Autowired
	public ConstraintServiceImpl(IBaseDao<Constraint> dao) {
		super(dao);
		this.constraintDao = (ConstraintDao)dao;
	}

	/**
	 * 获取表字段的约束条件
	 * @param clazz 表Entity类
	 */
	@Override
	public List<FieldConstraint> getList(Class<?> clazz) {
		// 获取所有约束数据
		if (constraints == null) {
			constraints = constraintDao.getList();
		}
		
		// 查找实体类的约束
		String tableName = EntityUtils.getTableName(clazz);		
		List<FieldConstraint> classConstrains = new ArrayList<FieldConstraint>();
		if (StringUtils.isNotEmpty(tableName)) {
			for (Constraint constraint : constraints) {
				if (constraint.getTableName().equals(tableName)) {
					FieldConstraint item = new FieldConstraint();
					item.setFiledName(constraint.getFieldName());
					item.setConstraint(constraint.getConstraint());
					classConstrains.add(item);
				}
			}
		}		
		return classConstrains;
	}
		
}