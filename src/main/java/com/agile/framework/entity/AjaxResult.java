package com.agile.framework.entity;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

import com.agile.framework.utils.JsonUtil;
import org.springframework.validation.BindingResult;

public class AjaxResult extends Result {
	
	public AjaxResult() {
		super();
	}

	/**
     * 构造函数（Object对象数据）
     * @param data
	 */
	public AjaxResult(Object data) {
		super(data);
	}

	/**
	 * 构造函数（BindingResult数据）
     * @param bindingResult
	 */
	public AjaxResult(BindingResult bindingResult) {
        add(bindingResult);
	}

	public AjaxResult(Exception e) {
		super(e);
	}	

	public String toJson() {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(this);
		} catch (IOException e) {
			json = JsonUtil.toJson(new AjaxResult(e));
		}
		return json;
	}
}
