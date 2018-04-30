/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.agile.framework.persistence.BaseEntity;

// THIS CODE IS GENERATED BY CODE GENERATOR.

@Table(name="sys_user_role")
@Entity
public class UserRole extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;
	private Integer roleId;
	private Integer sort;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private String deleted;

	public UserRole() {
	
	}

	public UserRole(Integer roleId){
		this.roleId = roleId;
	}
    
    @Column(name="id", columnDefinition="int", nullable = true, length = 10)
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}
    
    @Column(name="user_id", columnDefinition="int", nullable = false, length = 10)
	public Integer getUserId(){
		return userId;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id", columnDefinition="int", nullable = false, length = 10)
	public Integer getRoleId(){
		return roleId;
	}

	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
    
    @Column(name="sort", columnDefinition="int", nullable = true, length = 10)
	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}
    
    @Column(name="create_by", columnDefinition="varchar", nullable = true, length = 50)
	public String getCreateBy(){
		return createBy;
	}

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
    
    @Column(name="create_time", columnDefinition="datetime", nullable = true, length = 19)
	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
    
    @Column(name="update_by", columnDefinition="varchar", nullable = true, length = 50)
	public String getUpdateBy(){
		return updateBy;
	}

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
    
    @Column(name="update_time", columnDefinition="datetime", nullable = true, length = 19)
	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
    
    @Column(name="deleted", columnDefinition="varchar", nullable = true, length = 255)
	public String getDeleted(){
		return deleted;
	}

	public void setDeleted(String deleted){
		this.deleted = deleted;
	}
}
