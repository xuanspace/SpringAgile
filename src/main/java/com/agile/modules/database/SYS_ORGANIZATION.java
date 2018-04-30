/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_ORGANIZATION extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_organization", "id"); 
	public final static SQLField<Integer> PARENT_ID = new SQLField<Integer>("sys_organization", "parent_id"); 
	public final static SQLField<String> NAME = new SQLField<String>("sys_organization", "name"); 
	public final static SQLField<String> DESCRIPTION = new SQLField<String>("sys_organization", "description"); 
	public final static SQLField<String> PATH = new SQLField<String>("sys_organization", "path"); 
	public final static SQLField<Integer> TYPE = new SQLField<Integer>("sys_organization", "type"); 
	public final static SQLField<String> CODE = new SQLField<String>("sys_organization", "code"); 
	public final static SQLField<Integer> SORT = new SQLField<Integer>("sys_organization", "sort"); 
	public final static SQLField<Integer> LEVEL = new SQLField<Integer>("sys_organization", "level"); 
	public final static SQLField<String> CREATE_BY = new SQLField<String>("sys_organization", "create_by"); 
	public final static SQLField<Date> CREATE_TIME = new SQLField<Date>("sys_organization", "create_time"); 
	public final static SQLField<String> UPDATE_BY = new SQLField<String>("sys_organization", "update_by"); 
	public final static SQLField<Date> UPDATE_TIME = new SQLField<Date>("sys_organization", "update_time"); 
	public final static SQLField<String> DELETED = new SQLField<String>("sys_organization", "deleted"); 

	@Override
	public String getName() {
		return "sys_organization";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_organization";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,PARENT_ID,NAME,DESCRIPTION,PATH,TYPE,CODE,SORT,LEVEL,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,DELETED,
		}; 
		return fields;
	}	
}
