/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.framework.persistence;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.JdbcUtils;
import antlr.collections.List;

/**
 *  基于JdbcTemplate模板通用Dao接口类
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseJdbcDao<T, ID extends Serializable> extends JdbcDaoSupport implements BaseDao<T, ID>{

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    // 根据查询结果返回全部字段
	@SuppressWarnings("rawtypes")
	public List extractData(ResultSet rs, int size) throws SQLException, DataAccessException {        
		List objList = (List) new ArrayList<Map>(size);  
        try {  
            while (rs.next()) {  
                Map<String, Object> map = new HashMap<String, Object>();
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int columnCount = rsMetaData.getColumnCount();
                for(int colIndex = 1; colIndex <= columnCount; colIndex ++){  
                    String colName = rsMetaData.getColumnName(colIndex);  
                    Object value = JdbcUtils.getResultSetValue(rs, colIndex);  
                    map.put(colName, value);  
                }
                objList.add(map);  
            }
        } catch (Throwable e) {  
            throw new SQLException("Map result set error！", e);
        }   
        return objList;  
    }  
	
	@SuppressWarnings("rawtypes")
	public Object load(Class cls, String id) {
		Object result = null;
		return result;
	}	
}
