/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_USER extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<String> NAME = new Field<String>("name"); 
	public final static Field<String> PASSWORD = new Field<String>("password"); 
	public final static Field<Integer> TYPE = new Field<Integer>("type"); 
	public final static Field<Integer> STATUS = new Field<Integer>("status"); 
	public final static Field<String> LOGIN_NAME = new Field<String>("login_name"); 
	public final static Field<String> LOGIN_IP = new Field<String>("login_ip"); 
	public final static Field<Date> LOGIN_DATE = new Field<Date>("login_date"); 
	public final static Field<String> LOGIN_ALLOW = new Field<String>("login_allow"); 
	public final static Field<String> CREATE_BY = new Field<String>("create_by"); 
	public final static Field<Date> CREATE_DATE = new Field<Date>("create_date"); 
	public final static Field<String> UPDATE_BY = new Field<String>("update_by"); 
	public final static Field<Date> UPDATE_DATE = new Field<Date>("update_date"); 
	public final static Field<String> DELETED = new Field<String>("deleted"); 
	public final static Field<String> REMARK = new Field<String>("remark"); 

	@Override
	public String getName() {
		return "sys_user";
	}

	@Override
	public String getComment() {
		return null;
	}
}
