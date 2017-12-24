/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import com.agile.framework.query.Schema;

public class Tables implements Schema {

    public static final Tables Tables = new Tables();
    
	public final static SYS_AREA SYS_AREA = new SYS_AREA(); 
	public final static SYS_CONFIG SYS_CONFIG = new SYS_CONFIG(); 
	public final static SYS_DICT SYS_DICT = new SYS_DICT(); 
	public final static SYS_GROUP SYS_GROUP = new SYS_GROUP(); 
	public final static SYS_LOG SYS_LOG = new SYS_LOG(); 
	public final static SYS_MENU SYS_MENU = new SYS_MENU(); 
	public final static SYS_MODULE SYS_MODULE = new SYS_MODULE(); 
	public final static SYS_OPERATION SYS_OPERATION = new SYS_OPERATION(); 
	public final static SYS_ORG_ROLE SYS_ORG_ROLE = new SYS_ORG_ROLE(); 
	public final static SYS_ORGANIZATION SYS_ORGANIZATION = new SYS_ORGANIZATION(); 
	public final static SYS_PERMISSION SYS_PERMISSION = new SYS_PERMISSION(); 
	public final static SYS_ROLE SYS_ROLE = new SYS_ROLE(); 
	public final static SYS_ROLE_MENU SYS_ROLE_MENU = new SYS_ROLE_MENU(); 
	public final static SYS_USER SYS_USER = new SYS_USER(); 
	public final static SYS_USER_ATTRIBUTE SYS_USER_ATTRIBUTE = new SYS_USER_ATTRIBUTE(); 
	public final static SYS_USER_CONTACT SYS_USER_CONTACT = new SYS_USER_CONTACT(); 
	public final static SYS_USER_DETAIL SYS_USER_DETAIL = new SYS_USER_DETAIL(); 
	public final static SYS_USER_GROUP SYS_USER_GROUP = new SYS_USER_GROUP(); 
	public final static SYS_USER_ORG SYS_USER_ORG = new SYS_USER_ORG(); 
	public final static SYS_USER_ROLE SYS_USER_ROLE = new SYS_USER_ROLE(); 
}
