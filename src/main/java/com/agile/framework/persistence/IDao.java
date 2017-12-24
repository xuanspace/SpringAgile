package com.agile.framework.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基本数据访问接口
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0
 */
public interface IDao <T> {

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
    
}