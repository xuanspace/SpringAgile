package com.agile.framework.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;

public interface IDaoService<T> extends IService {
	
	public abstract IBaseDao<T> getDao();
	
	public abstract T get(Serializable id);

    public abstract boolean exists(Serializable id);
    
    public abstract void update(T entity);

    public abstract void update(Collection<T> entities);
    
    public abstract void save(T entity);

    public abstract void save(Collection<T> entities);
       
    public abstract void delete(Serializable id);
    
    public abstract void delete(T entity);
    
    public abstract void delete(Collection<T> entities);
       
    public abstract long deleteAll();
    
    public abstract long getCount();
    
    public abstract List<T> getList();
    
	public abstract <M> M get(Class<M> clazz, Serializable id);
	
	public abstract <M> boolean exists(Class<M> clazz, Serializable id);
	
	public abstract <M> void delete(Class<M> clazz, Serializable id);
	
	public abstract <M> long deleteAll(Class<M> clazz);
	
	public abstract long getCount(Builder builder);
		
    public abstract List<T> getList(Builder builder);
    
    public abstract List<?> query(Builder builder);

    public abstract long execute(Builder builder);

    public abstract Builder queryBuilder();    
}
