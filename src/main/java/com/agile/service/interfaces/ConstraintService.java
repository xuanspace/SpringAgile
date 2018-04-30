/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.service.interfaces;

import com.agile.model.Constraint;

import java.util.List;

import com.agile.framework.service.IDaoService;
import com.agile.framework.validate.FieldConstraint;

public interface ConstraintService extends IDaoService<Constraint> {
	
	/**
	 * 获取表字段的约束条件
	 * @param clazz 表Entity类
	 */
	List<FieldConstraint> getList(Class<?> clazz);
}
