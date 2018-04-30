package com.agile.framework.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import com.agile.framework.utils.EntityUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.agile.framework.query.Builder;

/**
 * 基于Hibernate数据访问抽象类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0
 */
@SuppressWarnings({"unchecked" })
public class AbstractHibernateDao<T> implements IBaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    
    protected Class<T> entityClass;
	
    /**
     * By defining this class as abstract, we prevent Spring from creating instance of this class
     * If not defined abstract getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor with parameters.
     */    
    public AbstractHibernateDao() {
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
		return sessionFactory.getClassMetadata(clazz).getIdentifierPropertyName();
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
        return sessionFactory.getCurrentSession();
    }
    
	@Override
	public T get(Serializable id) {
        return (T) this.getSession().get(getEntityClass(), id);
	}

	@Override
	public boolean exists(Serializable id) {
    	return get(id) == null ? false : true;
	}

	@Override
	public void update(T entity) {
        this.getSession().update(entity);
        this.getSession().flush();
	}

	@Override
	public void update(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().update(entity);        
        }
        this.getSession().flush();
	}

	@Override
	public void save(T entity) {
        this.getSession().save(entity);
        this.getSession().flush();
	}

	@Override
	public void save(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().save(entity);
        }
        this.getSession().flush();
	}

	@Override
	public void delete(Serializable id) {
		String name = getEntityClass().getSimpleName();
		String key = EntityUtils.getIdName(entityClass);
		if (key != null) {
			String hql = "delete " + name + " t where t." + key + " = ?";
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, id);
			query.executeUpdate();
		}
	}

	@Override
	public void delete(T entity) {
        this.getSession().delete(entity);
        this.getSession().flush();
	}

	@Override
	public void delete(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().delete(entity);
        }
        this.getSession().flush();
	}

	@Override
	public long deleteAll() {
		String name = getEntityClass().getSimpleName();;
		String hql = String.format("delete from %s", name);
		Query query = this.getSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public long getCount() {
		Criteria criteria = this.getSession().createCriteria(getEntityClass());
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public List<T> getList() {
        Criteria criteria = this.getSession().createCriteria(getEntityClass());        
        return criteria.list();		
	}

	@Override
	public <M> M get(Class<M> clazz, Serializable id) {
        return (M)this.getSession().get(clazz, id);
	}

	@Override
	public <M> boolean exists(Class<M> clazz, Serializable id) {
		return get(clazz, id) == null ? false : true;
	}

	@Override
	public <M> void delete(Class<M> clazz, Serializable id) {
		M entity = get(clazz, id);
		if (entity != null) {
			this.getSession().delete(entity);
			this.getSession().flush();
		}
	}

	@Override
	public <M> long deleteAll(Class<M> clazz) {
		String name = getEntityClass().getSimpleName();;
		String hql = String.format("delete from %s", name);
		Query query = this.getSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public long getCount(Builder builder) {		
		Criteria criteria = this.getSession().createCriteria(builder.toHql());
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public List<T> getList(Builder builder) {
		String sql = builder.toNoPageSql();
        Query query = this.getSession().createSQLQuery(sql);        
        if (builder.getOffset() != null)
            query.setFirstResult(builder.getOffset());
        if (builder.getLimit() != null)
            query.setMaxResults(builder.getLimit());
        return query.list(); 
	}

	@Override
	public List<?> query(Builder builder) {
    	try {
			if (builder.isHql()) {
                String hql = builder.toNoPageHql();
                Query query = this.getSession().createQuery(hql);
                if (builder.getOffset() != null)
                    query.setFirstResult(builder.getOffset());
                if (builder.getLimit() != null)
                    query.setMaxResults(builder.getLimit());
                return query.list();
			}
			else {
                String sql = builder.toNoPageSql();
                SQLQuery query = this.getSession().createSQLQuery(sql);
                if (builder.getResultClass() != null) {
                	query.addEntity(builder.getResultClass());
                }else if (builder.getEntityClass() != null) {
                    query.addEntity(builder.getEntityClass());
                }
                if (builder.getOffset() != null)
                    query.setFirstResult(builder.getOffset());
                if (builder.getLimit() != null)
                    query.setMaxResults(builder.getLimit());
                return query.list();

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public long execute(Builder builder) {
		Query query = null;
		if (builder.isHql()) {
			query = this.getSession().createSQLQuery(builder.toHql());
		}
		else {
			query = this.getSession().createSQLQuery(builder.toSql());
		}
		return query.executeUpdate();
	}

	@Override
	public Builder queryBuilder() {
		Builder builder = new Builder(this);
		return builder;
	}
}
