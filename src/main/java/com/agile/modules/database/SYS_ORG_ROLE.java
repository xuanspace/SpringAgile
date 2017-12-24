/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_ORG_ROLE extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<Integer> ORG_ID = new Field<Integer>("org_id"); 
	public final static Field<Integer> ROLE_ID = new Field<Integer>("role_id"); 
	public final static Field<String> DESCRIPTION = new Field<String>("description"); 

	@Override
	public String getName() {
		return "sys_org_role";
	}

	@Override
	public String getComment() {
		return null;
	}
}
