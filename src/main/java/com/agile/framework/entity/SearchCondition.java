package com.agile.framework.entity;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.agile.framework.utils.EntityUtils;
import com.agile.framework.utils.StringUtils;

public class SearchCondition {
	// 字段名
	private String fieldName;

	// 字段值
	private String fieldValue;

	// 字段类型
	private String fieldType;
	
	// 字段操作符
	private String fieldOperator;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldOperator() {
		return fieldOperator;
	}

	public void setFieldOperator(String fieldCondition) {
		this.fieldOperator = fieldCondition;
	}

	public void fromJson(String jsonString) {
		
	}

	
}
