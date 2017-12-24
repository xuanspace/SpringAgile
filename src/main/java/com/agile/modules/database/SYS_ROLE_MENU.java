/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;


import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_ROLE_MENU extends Table {

	public final static Field<Integer> ROLE_ID = new Field<Integer>("role_id"); 
	public final static Field<Integer> MENU_ID = new Field<Integer>("menu_id"); 

	@Override
	public String getName() {
		return "sys_role_menu";
	}

	@Override
	public String getComment() {
		return null;
	}
}
