/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_ROLE_MENU extends SQLTable {

	public final static SQLField<Integer> ROLE_ID = new SQLField<Integer>("sys_role_menu", "role_id"); 
	public final static SQLField<Integer> MENU_ID = new SQLField<Integer>("sys_role_menu", "menu_id"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_role_menu", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_role_menu", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_role_menu", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_role_menu", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_role_menu", "deleted"); 

	@Override
	public String getName() {
		return "sys_role_menu";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_role_menu";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ROLE_ID,MENU_ID,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
