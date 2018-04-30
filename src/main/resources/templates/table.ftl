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

import com.agile.framework.query.SQLField;
import com.agile.framework.query.SQLTable;

public class ${tableName?upper_case} extends SQLTable {

	<#list columns as column>
	public final static SQLField<${column.javaType.simpleName}> ${column.name?upper_case} = new SQLField<${column.javaType.simpleName}>("${tableName}", "${column.name?lower_case}"); 
	</#list>

	@Override
	public String getName() {
		return "${tableName}";
	}

	@Override
	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
	    return "${tableName}";
    }
	
	public SQLField<?>[] getFileds() {
		SQLField<?>[] fields = {
			<#list columns as column>${column.name?upper_case},</#list>
		}; 
		return fields;
	}	
}
