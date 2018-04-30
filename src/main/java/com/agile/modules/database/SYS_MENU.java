/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_MENU extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_menu", "id"); 
	public final static SQLField<Integer> PARENT_ID = new SQLField<Integer>("sys_menu", "parent_id"); 
	public final static SQLField<String> PATH = new SQLField<String>("sys_menu", "path"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_menu", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_menu", "description"); 
	public final static SQLField<String> CODE = new SQLField<String>("sys_menu", "code"); 
	public final static SQLField<Integer> SORT = new SQLField<Integer>("sys_menu", "sort"); 
	public final static SQLField<String> HREF = new SQLField<String>("sys_menu", "href"); 
	public final static SQLField<String> TARGET = new SQLField<String>("sys_menu", "target"); 
	public final static SQLField<String> ICON = new SQLField<String>("sys_menu", "icon"); 
	public final static SQLField<String> VISIBLE = new SQLField<String>("sys_menu", "visible"); 
	public final static SQLField<String> PERMISSION = new SQLField<String>("sys_menu", "permission"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_menu", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_menu", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_menu", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_menu", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_menu", "deleted"); 

	@Override
	public String getName() {
		return "sys_menu";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_menu";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,PARENT_ID,PATH,NAME,DESCRIPTION,CODE,SORT,HREF,TARGET,ICON,VISIBLE,PERMISSION,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
