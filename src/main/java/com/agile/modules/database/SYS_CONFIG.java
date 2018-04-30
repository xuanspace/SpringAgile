/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_CONFIG extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_config", "id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_config", "name"); 
	public final static SQLField<String> VALUE = new SQLField<String>("sys_config", "value"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_config", "description"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_config", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_config", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_config", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_config", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_config", "deleted"); 

	@Override
	public String getName() {
		return "sys_config";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_config";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,NAME,VALUE,DESCRIPTION,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
