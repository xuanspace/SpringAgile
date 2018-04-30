/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_USER_GROUP extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_user_group", "id"); 
	public final static SQLField<String> PARENT_ID = new SQLField<String>("sys_user_group", "parent_id"); 
	public final static SQLField<Integer> USER_ID = new SQLField<Integer>("sys_user_group", "user_id"); 
	public final static SQLField<String> GROUP_ID = new SQLField<String>("sys_user_group", "group_id"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_user_group", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_user_group", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_user_group", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_user_group", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_user_group", "deleted"); 

	@Override
	public String getName() {
		return "sys_user_group";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_user_group";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,PARENT_ID,USER_ID,GROUP_ID,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
