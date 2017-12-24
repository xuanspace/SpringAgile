/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.framework.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *  基于模板通用Dao接口类
 */
public interface BaseDao<T, ID extends Serializable> {

    /**
     * 根据主键获取对象
     * @param id 实体的id
     * @return 实体对象
     */
    public abstract T get(ID id);

    /**
     * 根据查询语句获取实体
     * @param sql 查询语句
     * @param values 不定参数的Object数组
     * @return 实体对象
     */
    public abstract T get(String sql, Object... values);

    /**
     * 获取全部实体列表
     * @return 查询结果List集合
     */    
    public abstract List<T> getList(); 

    /**
     * 自定义参数查询返回结果
     * @param params 查询参数对象
     * @return 自定义返回对象
     */
    public abstract Object getList(Object params); 
    
    /**
     * 根据查询语句，获取对应的list
     * @param sql 查询语句
     * @param values 不定参数的Object数组
     * @return 查询结果List集合
     */
    public abstract List<T> getList(String sql, Object... values);

    /**
     * 缺省分页查询
     * @param pageIndex 分页索引
     * @param pageSize 分页大小
     * @return 查询结果List集合
     */    
    public List<T> getList(int pageIndex, int pageSize);

    /**
     * 根据SQL语句分页查询
     * @param sql 查询语句
     * @param pageIndex 分页索引
     * @param pageSize 分页大小
     * @param values 不定参数数组
     * @return 查询结果List集合
     */
    public abstract List<T> getList(String sql, int pageIndex, int pageSize, Object... values);   
    
    /**
     * 是否包含实体对象
     * @param t 实体对象
     * @return 是否包含
     */
    public abstract boolean contains(T t);
    
    /**
     * 刷新实体属性
     * @param t 实体对象
     */
    public abstract void refresh(T t);

    /**
     * 更新实体对象
     * @param t 实体对象
     */
    public abstract void update(T t);

    /**
     * 批量更新实体
     * @param entities 实体集合
     */
    public abstract void update(Collection<T> entities);
    
    /**
     * 保存实体对象
     * @param t 实体对象
     */
    public abstract void save(T t);

    /**
     * 批量保存实体
     * @param entities 实体集合
     */
    public abstract void save(Collection<T> entities);
    
    /**
     * 保存或者更新实体
     * @param t 实体对象
     */
    public abstract void saveOrUpdate(T t);

    /**
     * 批量保存或者更新实体
     * @param entities 实体集合
     */
    public abstract void saveOrUpdate(Collection<T> entities);
    
    /**
     * 根据实体对象删除数据
     * @param t 实体
     */
    public abstract void delete(T t);

    /**
     * 根据ID删除数据
     * @param Id 实体id
     * @return 是否删除成功
     */
    public abstract boolean delete(ID Id);

    /**
     * 批量删除
     * @param entities 实体集合
     */
    public abstract void delete(Collection<T> entities);

    /**
     * 获取所有记录数
     * @return 记录总数
     */
    public abstract long count();
    
    /**
     * 执行查询语句，获取记录数
     * @param sql 查询语句
     * @param values 不定参数数组
     * @return 记录总数
     */
    public abstract long count(String sql, Object... values);

    /**
     * 执行查询语句
     * @param sql 执行语句
     * @param values 不定参数数组
     */
    public abstract void execute(String sql, Object... values);
    
}