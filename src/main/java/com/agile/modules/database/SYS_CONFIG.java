/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_CONFIG extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<String> NAME = new Field<String>("name"); 
	public final static Field<String> VALUE = new Field<String>("value"); 

	@Override
	public String getName() {
		return "sys_config";
	}

	@Override
	public String getComment() {
		return null;
	}
}
