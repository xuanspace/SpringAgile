/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.framework.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.framework.persistence.BaseDao;

@Service("abstractService")
public abstract class AbstractService<T, ID extends Serializable> implements GenericService<T, ID> {

	protected BaseDao<T, ID> baseDao;

	public AbstractService() {
	}
	
    public AbstractService(BaseDao<T,ID> dao) {
        this.baseDao = dao;
    }
	
	@Override
    public T get(ID id) {
    	return baseDao.get(id);
    }

	@Override
    public T get(String sql, Object... values) {
    	return baseDao.get(sql, values);
    }

	@Override
	public List<T> getList() {
		return baseDao.getList();
	}

	@Override
	public Object getList(Object params) {
		return baseDao.getList(params);
	}
	
    public List<T> getList(String sql, Object... values) {
    	return baseDao.getList(sql, values);
    }
	
	public List<T> getList(int pageIndex, int pageSize) {
		return baseDao.getList(pageIndex, pageSize);
	}
	
    public  List<T> getList(String sql, int pageIndex, int pageSize, Object... values) {
    	return baseDao.getList(sql, pageIndex, pageSize, values);
    }
	
    public boolean contains(T t) {
        return baseDao.contains(t);
    }

    public void refresh(T t) {
    	baseDao.refresh(t);
    }

    public void update(T t) {
    	baseDao.update(t);
    }

    public void update(Collection<T> entities) {
    	baseDao.update(entities);
    }
	
    public void save(T t) {
    	baseDao.save(t);
    }

    public void save(Collection<T> entities) {
    	baseDao.save(entities);
    }
    
    public void saveOrUpdate(T t) {
    	baseDao.saveOrUpdate(t);
    }

    public void saveOrUpdate(Collection<T> entities) {
    	baseDao.saveOrUpdate(entities);
    }
	
    public void delete(T t) {
    	baseDao.delete(t);
    }
     
    public boolean delete(ID id) {
    	return baseDao.delete(id);
    }

    public void delete(Collection<T> entities) {
    	baseDao.delete(entities);
    }

	public long count() {
		return baseDao.count();
	}
	
    public long count(String sql, Object... values) {
    	return baseDao.count(sql, values);
    }

    public void execute(String sql, Object... values) {
    	baseDao.execute(sql, values);
    }
	
}
