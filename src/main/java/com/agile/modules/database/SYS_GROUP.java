/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_GROUP extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_group", "id"); 
	public final static SQLField<Integer> PARENT_ID = new SQLField<Integer>("sys_group", "parent_id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_group", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_group", "description"); 
	public final static SQLField<String> CODE = new SQLField<String>("sys_group", "code"); 
	public final static SQLField<Integer> ROLE = new SQLField<Integer>("sys_group", "role"); 
	public final static SQLField<Short> TYPE = new SQLField<Short>("sys_group", "type"); 
	public final static SQLField<Short> STATUS = new SQLField<Short>("sys_group", "status"); 
	public final static SQLField<Integer> SORT = new SQLField<Integer>("sys_group", "sort"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_group", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_group", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_group", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_group", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_group", "deleted"); 

	@Override
	public String getName() {
		return "sys_group";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_group";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,PARENT_ID,NAME,DESCRIPTION,CODE,ROLE,TYPE,STATUS,SORT,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
