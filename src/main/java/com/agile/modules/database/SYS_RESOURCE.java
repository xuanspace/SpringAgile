/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_RESOURCE extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_resource", "id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_resource", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_resource", "description"); 

	@Override
	public String getName() {
		return "sys_resource";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_resource";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,NAME,DESCRIPTION,
		}; 
		return fields;
	}	
}
