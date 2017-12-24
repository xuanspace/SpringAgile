<#--

Copyright (C) 2011-2015 Spring Agile

This file is part of Code Generator.

-->
/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

<#if hasDateType>	
import java.util.Date;
</#if>

import com.agile.framework.query.Field;
import com.agile.framework.query.Table;

public class ${tableName?upper_case} extends Table {

	<#list columns as column>
	public final static Field<${column.javaType.simpleName}> ${column.name?upper_case} = new Field<${column.javaType.simpleName}>("${column.name?lower_case}"); 
	</#list>

	@Override
	public String getName() {
		return "${tableName}";
	}

	@Override
	public String getComment() {
		return null;
	}
}
