/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_ORG_ROLE extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_org_role", "id"); 
	public final static SQLField<Integer> ORG_ID = new SQLField<Integer>("sys_org_role", "org_id"); 
	public final static SQLField<Integer> ROLE_ID = new SQLField<Integer>("sys_org_role", "role_id"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_org_role", "description"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_org_role", "deleted"); 

	@Override
	public String getName() {
		return "sys_org_role";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_org_role";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,ORG_ID,ROLE_ID,DESCRIPTION,DELETED,
		}; 
		return fields;
	}	
}
