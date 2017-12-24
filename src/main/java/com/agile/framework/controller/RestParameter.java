package com.agile.framework.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 *  HttpServletRequest的Restful请求参数类
 *
 *  获取Restful请求参数id,page,offset,size
 *  继承RequestParameter
 *   1. 获取常规请求参数
 *   2. 获取请求映射路径中的参数
 *
 */

public class RestParameter extends RequestParameter {

	public RestParameter(HttpServletRequest request) {
		super(request);
	}

	/**
	 * Restful请求参数id值
	 * @type 参数值类型
	 * @return object
	 */
	public Serializable getId(Class<?> type) {
		Serializable value = null;
		String name = type.getSimpleName();
		if (name.contains("Integer")){
			value = getVariable("id").toInt();
		}
		else if (name.contains("String")){
			value = getVariable("id").toInt();
		}
		return value;
	}

	/**
	 * Restful请求参数size值
	 * @return 分页大小值
	 */
	public Integer getSize() {
		return get("size").toInt();
	}

	/**
	 * Restful请求参数page值
	 * @return 分页索引值
	 */
	public Integer getPage() {
		return get("page").toInt();
	}
	
}
