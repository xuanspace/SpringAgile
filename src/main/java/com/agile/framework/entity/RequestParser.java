/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.framework.entity;

import javax.servlet.http.HttpServletRequest;

public class RequestParser {
		
	private HttpServletRequest request;
    
	public RequestParser(HttpServletRequest request) {	
		this.request = request;
	}

	String getString(String name) {
		return request.getParameter(name);
	}
	
	Integer getInt(String name) {
		String value = request.getParameter(name);
		if (value == null)
			return null;
		return Integer.parseInt(value);
	}
	
	Long getLong(String name) {
		String value = request.getParameter(name);
		if (value == null)
			return null;
		return Long.parseLong(value);
	}

	Boolean getBoolean(String name) {
		String value = request.getParameter(name);
		if (value == null)
			return null;		
		return Boolean.parseBoolean(value);
	}
	
}
