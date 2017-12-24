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

@Table(name="sys_dict")
@Entity
public class Dict extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer parentId;
	private String value;
	private String label;
	private String type;
	private String description;
	private Integer sort;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String deleted;

	public Dict() {
	
	}

	public Dict(Integer id){
		this.id = id;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition="int", nullable = false, length = 10)
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}
    
    @Column(name = "parent_id", columnDefinition="int", nullable = true, length = 10)
	public Integer getParentId(){
		return parentId;
	}

	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
    
    @Column(name = "value", columnDefinition="varchar", nullable = false, length = 100)
	public String getValue(){
		return value;
	}

	public void setValue(String value){
		this.value = value;
	}
    
    @Column(name = "label", columnDefinition="varchar", nullable = false, length = 100)
	public String getLabel(){
		return label;
	}

	public void setLabel(String label){
		this.label = label;
	}
    
    @Column(name = "type", columnDefinition="varchar", nullable = false, length = 100)
	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}
    
    @Column(name = "description", columnDefinition="varchar", nullable = false, length = 100)
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
    
    @Column(name = "sort", columnDefinition="int", nullable = true, length = 10)
	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}
    
    @Column(name = "create_by", columnDefinition="varchar", nullable = false, length = 64)
	public String getCreateBy(){
		return createBy;
	}

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
    
    @Column(name = "create_date", columnDefinition="datetime", nullable = false, length = 19)
	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
    
    @Column(name = "update_by", columnDefinition="varchar", nullable = false, length = 64)
	public String getUpdateBy(){
		return updateBy;
	}

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
    
    @Column(name = "update_date", columnDefinition="datetime", nullable = false, length = 19)
	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
    
    @Column(name = "deleted", columnDefinition="char", nullable = false, length = 1)
	public String getDeleted(){
		return deleted;
	}

	public void setDeleted(String deleted){
		this.deleted = deleted;
	}
}
