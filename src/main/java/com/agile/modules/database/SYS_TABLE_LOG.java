/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_TABLE_LOG extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_table_log", "id"); 
	public final static SQLField<Integer> TABLE_ID = new SQLField<Integer>("sys_table_log", "table_id"); 
	public final static SQLField<Integer> ROW_ID = new SQLField<Integer>("sys_table_log", "row_id"); 
	public final static SQLField<Integer> CREATE_BY = new SQLField<Integer>("sys_table_log", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_table_log", "create_time"); 
	public final static SQLField<Integer> UPDATE_BY = new SQLField<Integer>("sys_table_log", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_table_log", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_table_log", "deleted"); 

	@Override
	public String getName() {
		return "sys_table_log";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_table_log";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,TABLE_ID,ROW_ID,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
