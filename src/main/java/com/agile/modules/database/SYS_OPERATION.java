/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_OPERATION extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 

	@Override
	public String getName() {
		return "sys_operation";
	}

	@Override
	public String getComment() {
		return null;
	}
}
