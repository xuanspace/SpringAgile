/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_PERMISSION extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<String> OBJECT = new Field<String>("object"); 
	public final static Field<String> OPERATION = new Field<String>("operation"); 
	public final static Field<String> DESCRIPTION = new Field<String>("description"); 
	public final static Field<String> CREATE_BY = new Field<String>("create_by"); 
	public final static Field<Date> CREATE_DATE = new Field<Date>("create_date"); 
	public final static Field<String> UPDATE_BY = new Field<String>("update_by"); 
	public final static Field<Date> UPDATE_DATE = new Field<Date>("update_date"); 
	public final static Field<String> DELETED = new Field<String>("deleted"); 

	@Override
	public String getName() {
		return "sys_permission";
	}

	@Override
	public String getComment() {
		return null;
	}
}
