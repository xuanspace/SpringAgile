package com.agile.framework.persistence;

import java.io.Serializable;
import java.util.List;

import com.agile.framework.query.Builder;

/**
 * 通用数据访问接口
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0
 */
public interface IBaseDao<T> extends IDao<T> {
	
	public abstract Class<T> getEntityClass();
	
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
