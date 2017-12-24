/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_AREA extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<Integer> PARENT_ID = new Field<Integer>("parent_id"); 
	public final static Field<String> NAME = new Field<String>("name"); 
	public final static Field<String> DESCRIPTION = new Field<String>("description"); 
	public final static Field<String> PATH = new Field<String>("path"); 
	public final static Field<Integer> SORT = new Field<Integer>("sort"); 
	public final static Field<String> CODE = new Field<String>("code"); 
	public final static Field<Short> TYPE = new Field<Short>("type"); 
	public final static Field<String> CREATE_BY = new Field<String>("create_by"); 
	public final static Field<Date> CREATE_DATE = new Field<Date>("create_date"); 
	public final static Field<String> UPDATE_BY = new Field<String>("update_by"); 
	public final static Field<Date> UPDATE_DATE = new Field<Date>("update_date"); 
	public final static Field<String> DELETED = new Field<String>("deleted"); 

	@Override
	public String getName() {
		return "sys_area";
	}

	@Override
	public String getComment() {
		return null;
	}
}
