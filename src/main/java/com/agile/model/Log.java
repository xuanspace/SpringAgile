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

@Table(name="sys_log")
@Entity
public class Log extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	public Log() {
	
	}

	public Log(Integer id){
		this.id = id;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", columnDefinition="int", nullable = false, length = 10)
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}
}