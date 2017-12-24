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

@Table(name="sys_user_attribute")
@Entity
public class UserAttribute extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;
	private String name;
	private String value;
	private Short type;

	public UserAttribute() {
	
	}

	public UserAttribute(Integer id){
		this.id = id;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition="int", nullable = false, length = 10)
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}
    
    @Column(name = "user_id", columnDefinition="int", nullable = true, length = 10)
	public Integer getUserId(){
		return userId;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}
    
    @Column(name = "name", columnDefinition="varchar", nullable = true, length = 255)
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
    
    @Column(name = "value", columnDefinition="varchar", nullable = true, length = 255)
	public String getValue(){
		return value;
	}

	public void setValue(String value){
		this.value = value;
	}
    
    @Column(name = "type", columnDefinition="tinyint", nullable = true, length = 3)
	public Short getType(){
		return type;
	}

	public void setType(Short type){
		this.type = type;
	}
}
