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

@Table(name="sys_operation")
@Entity
public class Operation extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String description;

	public Operation() {
	
	}

	public Operation(Integer id){
		this.id = id;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition="int", nullable = false, length = 10)
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}
    
    @Column(name="name", columnDefinition="varchar", nullable = true, length = 50)
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
    
    @Column(name="description", columnDefinition="varchar", nullable = true, length = 255)
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
}
