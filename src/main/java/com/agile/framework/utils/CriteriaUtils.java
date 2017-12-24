/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.framework.utils;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.agile.framework.entity.DataTableParameter;
import com.agile.framework.entity.SearchCondition;

/**
 * Hibernate工具类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 2.3.3
 */
public class CriteriaUtils {

	/**
	 * 建立搜索查询表达式
	 * 
	 * @param criteria 查询对象
	 * @param search 搜索条件
	 * @return 表达式
	 */
	public static void addCriteria(Criteria criteria, SearchCondition search) {
		SimpleExpression expression = null;
		String fieldName = search.getFieldName();
		String fieldValue = search.getFieldValue();
		String fieldOperator = search.getFieldOperator();

		if (StringUtils.isEmpty(fieldName) || StringUtils.isEmpty(fieldValue))
			return;

		// 猜测未指定字段类型
		Object value = fieldValue;
		if (StringUtils.isEmpty(search.getFieldType())) {
			// 整数类型字符串
			if (StringUtils.isInteger(fieldValue)) {
				value = Integer.parseInt(fieldValue);
			}
			// 浮点类型字符串
			else if (StringUtils.isNumeric(fieldValue)) {
				value = Double.parseDouble(fieldValue);
			}
			// 字符串缺省like查询
			else {
				fieldOperator = "%";
			}
		}

		// 添加字段查询表达式
		switch (fieldOperator) {
		case "=":
			expression = Restrictions.eq(fieldName, value);
			break;
		case "<>":
			expression = Restrictions.ne(fieldName, value);
			break;
		case ">":
			expression = Restrictions.gt(fieldName, value);
			break;
		case ">=":
			expression = Restrictions.ge(fieldName, value);
			break;
		case "<":
			expression = Restrictions.lt(fieldName, value);
			break;
		case "<=":
			expression = Restrictions.le(fieldName, value);
			break;
		case "%":
			expression = Restrictions.like(fieldName, (String) value, MatchMode.ANYWHERE);
			break;
		default:
			expression = Restrictions.eq(fieldName, value);
		}
		criteria.add(expression);
	}

	/**
	 * 建立搜索查询表达式
	 * 
	 * @param criteria 查询对象
	 * @param search 搜索条件
	 * @param entityClazz 搜索的实体类
	 * @return 表达式
	 */
	public static void addCriteria(Criteria criteria, SearchCondition search, Class<?> entityClazz) {
		SimpleExpression expression = null;
		String fieldName = search.getFieldName();
		String fieldValue = search.getFieldValue();
		String operator = search.getFieldOperator();

		if (StringUtils.isEmpty(fieldName) || StringUtils.isEmpty(fieldValue))
			return;

		// 字段名匹配实体类属性
		Class<?> typeClazz = EntityUtils.getPropertyType(fieldName, entityClazz);
		Object value = StringUtils.toObject(fieldValue, typeClazz);
		if (StringUtils.isEmpty(operator)) {
			if (typeClazz.getSimpleName().equals("String")) {
				operator = "%";
			} else {
				operator = "=";
			}
		}

		// 添加字段查询表达式
		switch (operator) {
		case "=":
			expression = Restrictions.eq(fieldName, value);
			break;
		case "<>":
			expression = Restrictions.ne(fieldName, value);
			break;
		case ">":
			expression = Restrictions.gt(fieldName, value);
			break;
		case ">=":
			expression = Restrictions.ge(fieldName, value);
			break;
		case "<":
			expression = Restrictions.lt(fieldName, value);
			break;
		case "<=":
			expression = Restrictions.le(fieldName, value);
			break;
		case "%":
			expression = Restrictions.like(fieldName, (String) value, MatchMode.ANYWHERE);
			break;
		default:
			expression = Restrictions.eq(fieldName, value);
		}
		criteria.add(expression);
	}

	/**
	 * 建立搜索查询表达式
	 * 
	 * @param criteria 查询对象
	 * @param searchs 搜索条件
	 * @return 表达式
	 */
	public static void addCriterias(Criteria criteria, SearchCondition searchs[]) {
		if (searchs == null)
			return;

		for (int i = 0; i < searchs.length; i++) {
			addCriteria(criteria, searchs[i]);
		}
	}

	/**
	 * 建立搜索查询表达式
	 * 
	 * @param criteria 查询对象
	 * @param searchs 搜索条件
	 * @param entityClazz 搜索的实体类
	 * @return 表达式
	 */
	public static void addCriterias(Criteria criteria, SearchCondition searchs[], Class<?> entityClazz) {
		if (searchs == null)
			return;

		for (int i = 0; i < searchs.length; i++) {
			addCriteria(criteria, searchs[i], entityClazz);
		}
	}

	/**
	 * 建立排序查询表达式
	 * 
	 * @param criteria 查询对象
	 * @param sorts 排序条件
	 * @return 表达式
	 */
	public static void addCriterias(Criteria criteria, DataTableParameter.Sort sorts[]) {
		if (sorts == null)
			return;

		for (int i = 0; i < sorts.length; i++) {
			String name = sorts[i].getsSortName();
			String dir = sorts[i].getsSortDir();
			if (StringUtils.isEmpty(name) || StringUtils.isEmpty(dir))
				continue;
			if (dir.equals("asc"))
				criteria.addOrder(Order.asc(name));
			else
				criteria.addOrder(Order.desc(name));
		}
	}

}