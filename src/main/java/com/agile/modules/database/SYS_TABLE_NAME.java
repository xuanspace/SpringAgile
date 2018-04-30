/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_TABLE_NAME extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_table_name", "id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_table_name", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_table_name", "description"); 

	@Override
	public String getName() {
		return "sys_table_name";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_table_name";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,NAME,DESCRIPTION,
		}; 
		return fields;
	}	
}
