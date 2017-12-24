/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_GROUP extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<Integer> PARENT_ID = new Field<Integer>("parent_id"); 
	public final static Field<String> NAME = new Field<String>("name"); 
	public final static Field<String> DESCRIPTION = new Field<String>("description"); 
	public final static Field<String> CODE = new Field<String>("code"); 
	public final static Field<String> ROLE = new Field<String>("role"); 
	public final static Field<Short> TYPE = new Field<Short>("type"); 
	public final static Field<Short> STATUS = new Field<Short>("status"); 
	public final static Field<Integer> SORT = new Field<Integer>("sort"); 

	@Override
	public String getName() {
		return "sys_group";
	}

	@Override
	public String getComment() {
		return null;
	}
}
