/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agile.model.Role;
import com.agile.model.User;
import com.agile.model.UserRole;
import com.agile.service.interfaces.UserRoleService;
import com.agile.validator.UserRoleValidator;
import com.agile.framework.controller.AbstractDaoController;
import com.agile.framework.controller.RestParameter;
import com.agile.framework.entity.AjaxResult;
import com.agile.framework.query.Builder;

@Controller
@RequestMapping("/userRole")
public class UserRoleController extends AbstractDaoController<UserRole>{
	
	private UserRoleService userRoleService;
	private UserRoleValidator userRoleValidate;

	@Autowired
	public UserRoleController(UserRoleService service, UserRoleValidator validator) {
		super(service, validator);
		this.userRoleService = service;
		this.userRoleValidate = validator;
	}
	
    /**
     * 获取列表数据请求
     * @return list
     */    
	@ResponseBody
	@RequestMapping(value="/getUserRole", method= RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public AjaxResult getUserRole(HttpServletRequest request) throws Exception {
		AjaxResult result = new AjaxResult();
        RestParameter params = new RestParameter(request);
        Integer page = params.getPage();
        Integer size = params.getSize();
        if (page != null && size != null) {
        	User user = new User();
        	user.setId(6);
            List<Role> roles = userRoleService.getUserRoles(user);
            result.setData(roles);
        }else {
        	result.setError("参数错误");
        }
		return result;
    }
	
}
