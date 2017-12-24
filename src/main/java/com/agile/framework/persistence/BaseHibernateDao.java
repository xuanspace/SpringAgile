/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.framework.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.annotation.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.agile.framework.entity.DataTableParameter;
import com.agile.framework.entity.DataTableResponse;
import com.agile.framework.entity.SearchCondition;
import com.agile.framework.utils.CriteriaUtils;
import com.agile.framework.entity.DataTableParameter.Sort;

/**
 * 基于Hibernate模板基础类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 2.3.3
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseHibernateDao<T, ID extends Serializable> implements BaseDao<T, ID> {
     
    @Autowired
    private SessionFactory sessionFactory;
    protected Class<T> entityClass;

    /**
     * By defining this class as abstract, we prevent Spring from creating instance of this class
     * If not defined abstract getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor with parameters.
     */    
    public BaseHibernateDao() {
    	getEntityClass();
    }

    /**
     * @return the entity class
     */    
    protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    /**
     * @return the entity table name
     */    
    protected String getTableName() {
    	String tableName = "";
    	Annotation annos[] = getEntityClass().getAnnotations();
        for(Annotation a : annos) {
        	Class clazz = a.annotationType();
        	String name = clazz.getSimpleName();
        }
        return tableName;
    }
    
        
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    /**
     * @return session
     */
    public Session getSession() {
    	return sessionFactory.openSession();
        //return sessionFactory.getCurrentSession();
    }
    
    /**
     * 获取主键对象
     * @param id 实体id
     * @return 实体对象
     */
    @Override
    public T get(ID id) {
        T load = (T) this.getSession().get(getEntityClass(), id);
        return load;
    }

    /**
     * 根据查询语句获取实体
     * @param sql 查询语句
     * @param values 不定参数数组
     * @return 实体对象
     */
    @Override
    public T get(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    /**
     * 获取全部实体列表
     * @return 实体List集合
     */
    @Override
    public List<T> getList() {
        Criteria criteria = this.getSession().createCriteria(getEntityClass());        
        return criteria.list();
    }
    
    /**
     * 根据参数查询，返回实体列表
     * @param sql 查询语句
     * @param values 不定参数数组
     * @return 查询结果List集合
     */
    @Override
    public List<T> getList(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

    /**
     * 缺省分页查询
     * @param pageIndex 分页索引
     * @param pageSize 分页大小
     * @return 查询结果List集合
     */    
    @Override
    public List<T> getList(int pageIndex, int pageSize) {
        Criteria criteria = this.getSession().createCriteria(getEntityClass());
        return criteria.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
    }
    
    /**
     * 根据SQL语句分页查询
     * @param sql 查询语句
     * @param pageIndex 分页索引
     * @param pageSize 分页大小
     * @param values 不定参数数组
     * @return 查询结果List集合
     */
    @Override
    public  List<T> getList(String hql, int pageIndex, int pageSize, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
    }

    /**
     * 自定义参数查询返回结果
     * @param params 查询参数对象
     * @return 自定义返回对象
     */
    @Override
    public Object getList(Object params) {
    	Session session = this.getSession();
    	if (params == null) {
    		throw new RuntimeException("getList parameter object is null");
    	}
    	
        // 查询总数
		Criteria countCriteria = session.createCriteria(getEntityClass());
		countCriteria.setProjection(Projections.rowCount());
        Long count = (Long) countCriteria.uniqueResult();
        
        // 条件查询
        Criteria queryCriteria = session.createCriteria(getEntityClass());
        
        // 处理DataTable参数
        if (params instanceof DataTableParameter) {
        	DataTableParameter table = (DataTableParameter)params;

        	// 搜索参数
        	SearchCondition searchs[] = table.getSearchs();
        	CriteriaUtils.addCriterias(queryCriteria, searchs, getEntityClass());
        	
    		// 排序参数
    		DataTableParameter.Sort sorts[] = table.getSorts();
    		CriteriaUtils.addCriterias(queryCriteria, sorts);
    		
    		// 分页参数
        	Integer size = table.getiDisplayLength();
    		Integer page = table.getiDisplayStart()/size + 1;
    		queryCriteria.setFirstResult((page - 1) * size).setMaxResults(size);
            List data = queryCriteria.list();
            
            // 返回列表
            DataTableResponse result = new DataTableResponse();
			result.setsEcho(table.getsEcho());
			result.setiTotalRecords(count);
			result.setiTotalDisplayRecords(count);
			result.setAaData(data);
			return result;
        }
        
        return null;
    }
    
    /**
     * 是否包含实体对象
     * @param t 实体对象
     * @return 是否包含
     */
    @Override
    public boolean contains(T t) {
        return this.getSession().contains(t);
    }

    /**
     * 刷新实体属性
     * @param t 实体对象
     */
    @Override
    public void refresh(T t) {
        this.getSession().refresh(t);
    }

    /**
     * 更新实体对象
     * @param t 实体对象
     */
    @Override
    public void update(T t) {
        this.getSession().update(t);
        this.getSession().flush();
    }
    
    /**
     * 批量更新实体对象
     * @param entities 实体的Collection集合
     */
    @Override
    public void update(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().update(entity);        
        }
        this.getSession().flush();
    }
    
    /**
     * 保存实体对象
     * @param t 实体对象
     */
    @Override
    public void save(T t) {
        this.getSession().save(t);
        this.getSession().flush();
    }

    /**
     * 批量更新实体对象
     * @param entities 实体的Collection集合
     */
    @Override
    public void save(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().save(entity);
        }
        this.getSession().flush();
    }
    
    /**
     * 保存或者更新实体
     * @param t 实体对象
     */
    @Override
    public void saveOrUpdate(T t) {
        this.getSession().saveOrUpdate(t);
        this.getSession().flush();
    }

    /**
     * 批量保存或者更新实体
     * @param entities 实体的Collection集合
     */
    @Override
    public void saveOrUpdate(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().saveOrUpdate(entity);
        }
        this.getSession().flush();
    }
    
    /**
     * 根据实体对象删除数据
     * @param t 实体
     */
    @Override
    public void delete(T t) {
        this.getSession().delete(t);
        this.getSession().flush();
    }
     
    /**
     * 根据ID删除数据
     * @param Id 实体id
     * @return 是否删除成功
     */
    @Override
    public boolean delete(ID Id) {
         T t = get(Id);
         if(t == null){
             return false;
         }
         delete(t);
        return true;
    }

    /**
     * 批量删除
     * @param entities 实体的Collection集合
     */
    @Override
    public void delete(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().delete(entity);
        }
        this.getSession().flush();
    }

    /**
     * 获取所有记录数
     * @return 记录总数
     */   
	@Override
	public long count() {
		Criteria criteria = this.getSession().createCriteria(getEntityClass());
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
    /**
     * 执行查询语句，获取记录数
     * @param sql 查询语句
     * @param values 不定参数数组
     * @return 记录总数
     */
    @Override
    public long count(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        Long value = (Long) query.uniqueResult();
        return value;
    }

    /**
     * 执行原生SQL语句
     * @param hql 执行语句
     * @param values 不定参数数组
     */
    @Override
    public void execute(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null)         {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }
 
    /**
     * 
     * 设置语句的处理参数
     * 
     * @param statement
     * @param pos ?占位符索引，从0开始
     * @param value
     * @throws SQLException
     */
    @SuppressWarnings("unused")
	private void setParameter(PreparedStatement statement, int pos, Object value) throws SQLException
    {
        if (value == null) {
        	statement.setNull(pos + 1, Types.VARCHAR);
            return;
        }
        
        Class dataCls = value.getClass();
        if (String.class.equals(dataCls)) {
        	statement.setString(pos + 1, (String)value);
        }
        else if (boolean.class.equals(dataCls)) {
        	statement.setBoolean(pos + 1, ((Boolean)value));
        }
        else if (int.class.equals(dataCls)) {
        	statement.setInt(pos + 1, (Integer)value);
        }
        else if (double.class.equals(dataCls)) {
        	statement.setDouble(pos + 1, (Double)value);
        }
        else if (Date.class.equals(dataCls)) {
            Date val = (Date)value;
            statement.setTimestamp(pos + 1, new Timestamp(val.getTime()));
        }
        else if (BigDecimal.class.equals(dataCls)) {
        	statement.setBigDecimal(pos + 1, (BigDecimal)value);
        } else {
            // 未知类型
        	statement.setObject(pos + 1, value);
        }         
    }
    
}