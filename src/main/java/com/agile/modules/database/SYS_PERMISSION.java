/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_PERMISSION extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_permission", "id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_permission", "name"); 
	public final static SQLField<Integer> RESOURCE_ID = new SQLField<Integer>("sys_permission", "resource_id"); 
	public final static SQLField<Integer> OPERATION_ID = new SQLField<Integer>("sys_permission", "operation_id"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_permission", "description"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_permission", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_permission", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_permission", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_permission", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_permission", "deleted"); 

	@Override
	public String getName() {
		return "sys_permission";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_permission";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,NAME,RESOURCE_ID,OPERATION_ID,DESCRIPTION,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
