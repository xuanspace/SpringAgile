/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_CONSTRAINT extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_constraint", "id"); 
	public final static SQLField<String> TABLE_NAME = new SQLField<String>("sys_constraint", "table_name"); 
	public final static SQLField<String> FIELD_NAME = new SQLField<String>("sys_constraint", "field_name"); 
	public final static SQLField<String> CONSTRAINT = new SQLField<String>("sys_constraint", "constraint"); 
	public final static SQLField<String> MESSAGE = new SQLField<String>("sys_constraint", "message"); 

	@Override
	public String getName() {
		return "sys_constraint";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_constraint";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,TABLE_NAME,FIELD_NAME,CONSTRAINT,MESSAGE,
		}; 
		return fields;
	}	
}
