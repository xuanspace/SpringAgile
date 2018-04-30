/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_ROLE extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_role", "id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_role", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_role", "description"); 
	public final static SQLField<String> CODE = new SQLField<String>("sys_role", "code"); 
	public final static SQLField<Integer> SORT = new SQLField<Integer>("sys_role", "sort"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_role", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_role", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_role", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_role", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_role", "deleted"); 

	@Override
	public String getName() {
		return "sys_role";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_role";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,NAME,DESCRIPTION,CODE,SORT,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
