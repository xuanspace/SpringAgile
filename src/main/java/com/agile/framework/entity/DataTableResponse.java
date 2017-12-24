/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.framework.entity;

import com.alibaba.druid.support.json.JSONUtils;

/* 
 * DataTable Ajax请求返回结果
 *   https://datatables.net
 */
public class DataTableResponse {
	
	// 客服端请求序号
	private int sEcho;
	
	// 返回实际的行数
	private long iTotalRecords;
    
    // 过滤后实际行数
	private long iTotalDisplayRecords;
    
    // 返回的数据集
	private Object aaData;
    
	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Object getAaData() {
		return aaData;
	}

	public void setAaData(Object aaData) {
		this.aaData = aaData;
	}
	
	public String toJson() {		
		return JSONUtils.toJSONString(this);
	}
}
