/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_OPERATION extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_operation", "id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_operation", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_operation", "description"); 

	@Override
	public String getName() {
		return "sys_operation";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_operation";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,NAME,DESCRIPTION,
		}; 
		return fields;
	}	
}
