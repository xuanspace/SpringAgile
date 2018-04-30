/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_USER_ATTRIBUTE extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_user_attribute", "id"); 
	public final static SQLField<Integer> USER_ID = new SQLField<Integer>("sys_user_attribute", "user_id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_user_attribute", "name"); 
	public final static SQLField<Short> TYPE = new SQLField<Short>("sys_user_attribute", "type"); 
	public final static SQLField<String> VALUE = new SQLField<String>("sys_user_attribute", "value"); 

	@Override
	public String getName() {
		return "sys_user_attribute";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_user_attribute";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,USER_ID,NAME,TYPE,VALUE,
		}; 
		return fields;
	}	
}
