/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import java.util.Date;

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class SYS_USER_DETAIL extends SQLTable {

	public final static SQLField<Integer> ID = new SQLField<Integer>("sys_user_detail", "id"); 
	public final static SQLField<Integer> USER_ID = new SQLField<Integer>("sys_user_detail", "user_id"); 
	public final static SQLField<Integer> ORG_ID = new SQLField<Integer>("sys_user_detail", "org_id"); 
	public final static SQLField<Integer> GROUP_ID = new SQLField<Integer>("sys_user_detail", "group_id"); 
	public final static SQLField<String> NICK = new SQLField<String>("sys_user_detail", "nick"); 
	public final static SQLField<String> ENAME = new SQLField<String>("sys_user_detail", "ename"); 
	public final static SQLField<String> AVATAR = new SQLField<String>("sys_user_detail", "avatar"); 
	public final static SQLField<String> SEX = new SQLField<String>("sys_user_detail", "sex"); 
	public final static SQLField<Date> BIRTHDAY = new SQLField<Date>("sys_user_detail", "birthday"); 
	public final static SQLField<String> ADDRESS = new SQLField<String>("sys_user_detail", "address"); 

	@Override
	public String getName() {
		return "sys_user_detail";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "sys_user_detail";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			ID,USER_ID,ORG_ID,GROUP_ID,NICK,ENAME,AVATAR,SEX,BIRTHDAY,ADDRESS,
		}; 
		return fields;
	}	
}
