/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_USER_CONTACT extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_user_contact", "id"); 
	public final static SQLField<Integer> USER_ID = new SQLField<Integer>("sys_user_contact", "user_id"); 
	public final static SQLField<Integer> TYPE = new SQLField<Integer>("sys_user_contact", "type"); 
	public final static SQLField<String> INFORMATION = new SQLField<String>("sys_user_contact", "information"); 

	@Override
	public String getName() {
		return "sys_user_contact";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_user_contact";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,USER_ID,TYPE,INFORMATION,
		}; 
		return fields;
	}	
}
