/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class SYS_USER_DETAIL extends Table {

	public final static Field<Integer> ID = new Field<Integer>("id"); 
	public final static Field<Integer> USER_ID = new Field<Integer>("user_id"); 
	public final static Field<Integer> ORG_ID = new Field<Integer>("org_id"); 
	public final static Field<Integer> GROUP_ID = new Field<Integer>("group_id"); 
	public final static Field<String> NICK = new Field<String>("nick"); 
	public final static Field<String> ENAME = new Field<String>("ename"); 
	public final static Field<String> AVATAR = new Field<String>("avatar"); 
	public final static Field<String> SEX = new Field<String>("sex"); 
	public final static Field<Date> BIRTHDAY = new Field<Date>("birthday"); 

	@Override
	public String getName() {
		return "sys_user_detail";
	}

	@Override
	public String getComment() {
		return null;
	}
}
