/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_ROLE_PERMISSION extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_role_permission", "id"); 
	public final static SQLField<Integer> ROLE_ID = new SQLField<Integer>("sys_role_permission", "role_id"); 
	public final static SQLField<String> PERMISSION = new SQLField<String>("sys_role_permission", "permission"); 

	@Override
	public String getName() {
		return "sys_role_permission";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_role_permission";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,ROLE_ID,PERMISSION,
		}; 
		return fields;
	}	
}
