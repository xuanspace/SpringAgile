/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.framework.entity;

import com.google.common.collect.Maps;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;

public class Result {
	
	// 状态码
	public final static int SUCCESS = 0;
	public final static int FAILD = 1;
	public final static int EXCEPTION = 2;
	public final static int VALIDATE_FAILD = 3;
	
	// 返回状态 
	protected int status = SUCCESS;

	// 返回的消息
	protected String message = "success";

	// 返回的数据
	protected Object data = null;

	// 构造函数
	public Result() {
	}

	// 构造函数
	public Result(Object data) {
		this.data = data;
	}

	// 构造函数
	public Result(Exception e) {
		this.status = EXCEPTION;
		this.message = e.getMessage();
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result setSuccess() {
		this.message = "success";
		this.status = SUCCESS;
		this.data = null;
		return this;
	}
	
	public Result setSuccess(String message) {
		this.message = message;
		this.status = SUCCESS;
		this.data = null;
		return this;
	}

	public Result setError(String message) {
		this.message = message;
		this.status = FAILD;
		this.data = null;
		return this;
	}

	public Result setFail(String message) {
		this.message = message;
		this.status = FAILD;
		this.data = null;
		return this;
	}

	public Result setWarn(String message) {
		this.message = message;
		this.status = FAILD;
		this.data = null;
		return this;
	}

	public void setException(Exception e) {
		this.status = EXCEPTION;
		this.message = e.getMessage();
	}

	public void add(BindingResult bindingResult) {
		Map<String,String> fieldErrors = Maps.newHashMap();
		for(FieldError error : bindingResult.getFieldErrors()){
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}
		this.status = VALIDATE_FAILD;
		this.message = "validation failure";
		this.data = fieldErrors;
	}
}