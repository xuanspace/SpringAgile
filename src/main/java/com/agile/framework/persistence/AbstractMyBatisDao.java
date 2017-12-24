package com.agile.framework.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import com.agile.framework.query.Builder;
import com.agile.framework.utils.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 基于Mybatis数据访问抽象类
 * @author linweixuan@gmail.com
 * @date 2017-11-03
 * @version 1.0
 */

public class AbstractMyBatisDao <T> implements IBaseDao<T> {

    private SqlSessionFactory sessionFactory = null;

    protected Class<T> entityClass;

    /**
     * By defining this class as abstract, we prevent Spring from creating instance of this class
     * If not defined abstract getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor with parameters.
     */
    public AbstractMyBatisDao() {
        getEntityClass();
    }

    /**
     * @return the entity class
     */
    public Class<T> getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public String getEntityIdName(Class<?> clazz) {
        return EntityUtils.getIdClass(clazz).getSimpleName();
    }

    /**
     * @return the sessionFactory
     */
    public SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return session
     */
    public SqlSession getSession() {
        return sessionFactory.openSession();
    }


    @Override
	public T get(Serializable id) {
        T entity = null;
        SqlSession session = getSession();
        try {
            entity = session.selectOne("User.get", 10);
        } finally {
            session.close();
        }
        return entity;
	}

	@Override
	public boolean exists(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Collection<T> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Collection<T> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Collection<T> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<T> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <M> M get(Class<M> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <M> boolean exists(Class<M> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <M> void delete(Class<M> clazz, Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <M> long deleteAll(Class<M> clazz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCount(Builder builder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<T> getList(Builder builder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> query(Builder builder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long execute(Builder builder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Builder queryBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

}
