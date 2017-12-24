<#--

Copyright (C) 2011-2015 Spring Agile

This file is part of Code Generator.

-->
/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.modules.database;

import com.agile.framework.query.Schema;

public class Tables implements Schema {

    public static final Tables Tables = new Tables();
    
	<#list tables as table>
	public final static ${table?upper_case} ${table?upper_case} = new ${table?upper_case}(); 
	</#list>
}
