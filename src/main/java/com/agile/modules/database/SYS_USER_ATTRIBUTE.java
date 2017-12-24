/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_USER_ATTRIBUTE extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<Integer> USER_ID = new Field<Integer>("user_id"); 
	public final static Field<String> NAME = new Field<String>("name"); 
	public final static Field<String> VALUE = new Field<String>("value"); 
	public final static Field<Short> TYPE = new Field<Short>("type"); 

	@Override
	public String getName() {
		return "sys_user_attribute";
	}

	@Override
	public String getComment() {
		return null;
	}
}
