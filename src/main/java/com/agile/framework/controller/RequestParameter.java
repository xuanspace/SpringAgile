package com.agile.framework.controller;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *  HttpServletRequest参数获取封装类
 *
 *  1. 获取常规请求参数
 *  2. 获取请求映射路径中的参数
 *
 */

public class RequestParameter {

	Map<String, String> variables = null;

	HttpServletRequest request = null;
	
	public RequestParameter(HttpServletRequest request) {
		this.request = request;
		this.variables  = getPathVariable(request);
	}

	/**
	 * 获取请求常规参数的值
	 * @return StringValue
	 */
	public StringValue get(String name) {
		String val = request.getParameter(name);
		return new StringValue(val);
	}

	/**
	 * 获取请求映射路径中的参数
	 * 如@RequestMapping(value="{id}"
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getPathVariable(HttpServletRequest request) {
		NativeWebRequest webRequest = new ServletWebRequest(request);
		Map<String, String> uriTemplateVars = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		return uriTemplateVars;
	}

	/**
	 * 获取请求映射路径的参数值
	 * 如@RequestMapping(value="{id}"
	 * @return StringValue
	 */
    public StringValue getVariable(String name) {
        String val = variables.get(name);
        return new StringValue(val);
    }

}
