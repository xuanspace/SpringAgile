/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_AREA extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_area", "id"); 
	public final static SQLField<Integer> PARENT_ID = new SQLField<Integer>("sys_area", "parent_id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_area", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_area", "description"); 
	public final static SQLField<String> PATH = new SQLField<String>("sys_area", "path"); 
	public final static SQLField<Integer> SORT = new SQLField<Integer>("sys_area", "sort"); 
	public final static SQLField<String> CODE = new SQLField<String>("sys_area", "code"); 
	public final static SQLField<Short> TYPE = new SQLField<Short>("sys_area", "type"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_area", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_area", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_area", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_area", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_area", "deleted"); 

	@Override
	public String getName() {
		return "sys_area";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_area";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,PARENT_ID,NAME,DESCRIPTION,PATH,SORT,CODE,TYPE,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
