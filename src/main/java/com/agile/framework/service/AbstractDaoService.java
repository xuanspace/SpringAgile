package com.agile.framework.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;

public class AbstractDaoService<T> implements IDaoService<T> {
	
	protected IBaseDao<T> baseDao;

	public AbstractDaoService() {
	}
	
    public AbstractDaoService(IBaseDao<T> dao) {
        this.baseDao = dao;
    }

	@Override
	public IBaseDao<T> getDao() {
		return this.baseDao;
	}
	
	@Override
	public T get(Serializable id) {
		return baseDao.get(id);
	}

	@Override
	public boolean exists(Serializable id) {
		return baseDao.exists(id);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void update(Collection<T> entities) {
		baseDao.update(entities);
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);		
	}

	@Override
	public void save(Collection<T> entities) {
		baseDao.save(entities);		
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public void delete(Collection<T> entities) {
		baseDao.delete(entities);
	}

	@Override
	public long deleteAll() {
		return baseDao.deleteAll();
	}

	@Override
	public long getCount() {
		return baseDao.getCount();
	}

	@Override
	public List<T> getList() {
		return baseDao.getList();
	}

	@Override
	public <M> M get(Class<M> clazz, Serializable id) {
		return baseDao.get(clazz, id);
	}

	@Override
	public <M> boolean exists(Class<M> clazz, Serializable id) {
		return baseDao.exists(clazz, id);
	}

	@Override
	public <M> void delete(Class<M> clazz, Serializable id) {
		baseDao.delete(clazz, id);
	}

	@Override
	public <M> long deleteAll(Class<M> clazz) {
		return baseDao.deleteAll(clazz);
	}

	@Override
	public long getCount(Builder builder) {
		return baseDao.getCount(builder);
	}

	@Override
	public List<T> getList(Builder builder) {
		return baseDao.getList(builder);
	}

	@Override
	public List<?> query(Builder builder) {
		return baseDao.query(builder);
	}

	@Override
	public long execute(Builder builder) {
		return baseDao.execute(builder);
	}

	@Override
	public Builder queryBuilder() {
		return baseDao.queryBuilder();
	}

}