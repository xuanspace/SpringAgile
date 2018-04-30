/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_MODULE extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_module", "id"); 
	public final static SQLField<Integer> PARENT_ID = new SQLField<Integer>("sys_module", "parent_id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_module", "name"); 
	public final static SQLField<String> PATH = new SQLField<String>("sys_module", "path"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_module", "description"); 
	public final static SQLField<String> CODE = new SQLField<String>("sys_module", "code"); 
	public final static SQLField<Integer> SORT = new SQLField<Integer>("sys_module", "sort"); 
	public final static SQLField<String> HREF = new SQLField<String>("sys_module", "href"); 
	public final static SQLField<String> TARGET = new SQLField<String>("sys_module", "target"); 
	public final static SQLField<String> ICON = new SQLField<String>("sys_module", "icon"); 
	public final static SQLField<String> VISIBLE = new SQLField<String>("sys_module", "visible"); 
	public final static SQLField<String> PERMISSION = new SQLField<String>("sys_module", "permission"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_module", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_module", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_module", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_module", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_module", "deleted"); 

	@Override
	public String getName() {
		return "sys_module";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_module";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,PARENT_ID,NAME,PATH,DESCRIPTION,CODE,SORT,HREF,TARGET,ICON,VISIBLE,PERMISSION,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
