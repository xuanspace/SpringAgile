/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_DICT extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_dict", "id"); 
	public final static SQLField<Integer> PARENT_ID = new SQLField<Integer>("sys_dict", "parent_id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_dict", "name"); 
	public final static SQLField<String> CODE = new SQLField<String>("sys_dict", "code"); 
	public final static SQLField<String> VALUE = new SQLField<String>("sys_dict", "value"); 
	public final static SQLField<String> LABEL = new SQLField<String>("sys_dict", "label"); 
	public final static SQLField<Short> TYPE = new SQLField<Short>("sys_dict", "type"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_dict", "description"); 
	public final static SQLField<Integer> SORT = new SQLField<Integer>("sys_dict", "sort"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_dict", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_dict", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_dict", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_dict", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_dict", "deleted"); 

	@Override
	public String getName() {
		return "sys_dict";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_dict";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,PARENT_ID,NAME,CODE,VALUE,LABEL,TYPE,DESCRIPTION,SORT,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
