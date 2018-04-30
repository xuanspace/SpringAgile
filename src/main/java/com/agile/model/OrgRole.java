/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.agile.framework.persistence.BaseEntity;

// THIS CODE IS GENERATED BY CODE GENERATOR.

@Table(name="sys_org_role")
@Entity
public class OrgRole extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orgId;
	private Integer roleId;
	private String description;
	private String deleted;

	public OrgRole() {
	
	}

	public OrgRole(Integer id){
		this.id = id;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition="int", nullable = false, length = 10)
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}
    
    @Column(name="org_id", columnDefinition="int", nullable = true, length = 10)
	public Integer getOrgId(){
		return orgId;
	}

	public void setOrgId(Integer orgId){
		this.orgId = orgId;
	}
    
    @Column(name="role_id", columnDefinition="int", nullable = true, length = 10)
	public Integer getRoleId(){
		return roleId;
	}

	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
    
    @Column(name="description", columnDefinition="varchar", nullable = true, length = 255)
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
    
    @Column(name="deleted", columnDefinition="char", nullable = true, length = 1)
	public String getDeleted(){
		return deleted;
	}

	public void setDeleted(String deleted){
		this.deleted = deleted;
	}
}
