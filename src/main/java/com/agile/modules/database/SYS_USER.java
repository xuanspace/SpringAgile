/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_USER extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_user", "id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_user", "name"); 
	public final static SQLField<String> PASSWORD = new SQLField<String>("sys_user", "password"); 
	public final static SQLField<Integer> TYPE = new SQLField<Integer>("sys_user", "type"); 
	public final static SQLField<Integer> STATUS = new SQLField<Integer>("sys_user", "status"); 
	public final static SQLField<String> LOGIN_NAME = new SQLField<String>("sys_user", "login_name"); 
	public final static SQLField<String> LOGIN_IP = new SQLField<String>("sys_user", "login_ip"); 
	public final static SQLField<Date> LOGIN_TIME = new SQLField<Date>("sys_user", "login_time"); 
	public final static SQLField<String> LOGIN_ALLOW = new SQLField<String>("sys_user", "login_allow"); 
	public final static SQLField<Integer> VISIT_COUNT = new SQLField<Integer>("sys_user", "visit_count"); 
	public final static SQLField<String> REGISTER_TIME = new SQLField<String>("sys_user", "register_time"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_user", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_user", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_user", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_user", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_user", "deleted"); 
	public final static SQLField<String> REMARK = new SQLField<String>("sys_user", "remark"); 

	@Override
	public String getName() {
		return "sys_user";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_user";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,NAME,PASSWORD,TYPE,STATUS,LOGIN_NAME,LOGIN_IP,LOGIN_TIME,LOGIN_ALLOW,VISIT_COUNT,REGISTER_TIME,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,REMARK,
		}; 
		return fields;
	}	
}
