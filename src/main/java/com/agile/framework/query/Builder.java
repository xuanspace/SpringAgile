package com.agile.framework.query;

import java.util.*;

import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.utils.EntityUtils;

/**
 * 操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0 
 */
public class Builder {
	
	private Boolean isDefault = true;
	
	private Integer limit = null;
	
	private Integer offset = null;
	
	private List<Expression> expressions = null;

    private IBaseDao<?> baseDao = null;

    private Class<?> entityClass = null;
    
    private String hql = null;

	private String sql = null;
    
	public Builder() {
		expressions = new ArrayList<Expression>();
	}

	public Builder(Class<?> clazz) {
		expressions = new ArrayList<Expression>();
		this.entityClass = clazz;
	}

	public Builder(IBaseDao<?> dao) {
		expressions = new ArrayList<Expression>();
		this.baseDao = dao;
		if (dao != null) {
			this.entityClass = dao.getEntityClass();
		}
	}
		
	public Where selectFrom(String table) {
		isDefault = false;
    	Expression expression = new Expression();
    	expression.setLeft("select");
    	expression.setOperator("from");
    	expression.setRight(table);
    	expressions.add(expression);
    	Where where = new Where(this);
		return where;
	}

	public Where selectFrom(Table table) {
		isDefault = false;
    	Expression expression = new Expression();
    	expression.setLeft("select");
    	expression.setOperator("from");
    	expressions.add(expression);
        List<Expression> exps = table.getExpressions();
        expressions.addAll(exps);
    	Where where = new Where(this);
		return where;
	}

	public From select(String fields) {
		isDefault = false;
		Expression expression = new Expression();
		expression.setLeft("select");
		expression.setOperator("from");
		expression.setRight(fields);
		expressions.add(expression);
		From from = new From(this);
		return from;
	}

	public From select(Field<?>... fields) {
		isDefault = false;
		Expression expression = new Expression();
		expression.setLeft("select");
		expression.setOperator("from");
		expressions.add(expression);		
		From from = new From(this);
        for(int i = 0; i < fields.length; i++) {
    		expressions.addAll(fields[i].getExpressions());
        }        
		return from;
	}

	public Update update(Table table) {
		isDefault = false;
		Expression expression = new Expression();
		expression.setLeft("update");
		expression.setRight(table);
		expressions.add(expression);
		Update update = new Update(this);
		return update;
	}

	public Where delete(Table table) {
		isDefault = false;
		Expression expression = new Expression();
		expression.setLeft("delete");
        expression.setOperator("from");
		expression.setRight(table);
		expressions.add(expression);
		Where where = new Where (this);
		return where ;
	}
	
	public Builder where(Field<?> field) {
		isDefault = false;
        Expression expression = new Expression();
        expression.setOperator("where");
        expressions.add(expression);

		List<Expression> exps = field.getExpressions();
		expressions.addAll(exps);
		return this;
	}
	
	public Builder limit(int size) {
		Expression expression = new Expression();
		expression.setOperator("limit");
		expression.setRight(size);
		expressions.add(expression);
		this.limit = size;
		return this;
	}

	public Builder offset(int value) {
		Expression expression = new Expression();
		expression.setOperator("offset");
		expression.setRight(value);
		expressions.add(expression);
		this.offset = value;
		return this;
	}

	public Builder order(Field<?> field) {
		Expression expression = new Expression();
		expression.setOperator("oder by");
		expression.setRight(field);
		expressions.add(expression);
		return this;
	}

	public Builder sql(String sql) {
		this.sql = sql;
		return this;
	}

	public Builder hql(String hql) {
		this.hql = hql;
		return this;
	}

	public boolean isSql() {
		if (this.sql != null)
			return true;
		return false;
	}

	public boolean isHql() {
		if (this.hql != null)
			return true;
		return false;
	}

	public List<?> list() {
		return this.baseDao.query(this);
	}

	public long execute() {
		return this.baseDao.execute(this);
	}

	public void setParameter(int position, Object value) {
		
	}
	
	public void setParameter(String name, Object value) {
		
	}
	
	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public IBaseDao<?> getDao() {
		return baseDao;
	}

	public void setDao(IBaseDao<?> dao) {
		this.baseDao = dao;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
    public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	private String toString(boolean pagable) {
		String str = "";
		if (expressions == null)
			return str;

		//Expression first = expressions.get(0);
		//if (first != null && first.getOperator().startsWith("where")) {
		if (isDefault) {
			str = "select * from ";
			if (entityClass != null) {
				str += EntityUtils.getTableName(entityClass);
			}
		}

		for (Expression exp: expressions) {
			if (pagable == false) {
				if (exp.getOperator().equals("limit") || exp.getOperator().equals("offset"))
					continue;
			}
			String statment = exp.toString();
			if (str.length() == 0 || statment.startsWith(" "))
				str += statment;
			else
				str += " " + statment;
		}
		if (str.startsWith(" "))
			str = str.trim();
		return str;
	}
	
	public String toString() {
		return toString();
	}

	public String toSql() {
		return toString();
	}

	public String toSql(boolean pagable) {
		return toString(pagable);
	}
	
	public String toHql() {
		return toString();
	}

	/**
	 * 转成HQL语句
	 * @param pagable 是否带分页
	 * @return  返回hql语句
	 */
	public String toHql(boolean pagable) {
		String str = "";
		// 直接设置hql语句
		if (getHql() != null) {
			str = getHql();
		}
		// 通过拼接hql语句
		else{
			if (pagable) {
				str = toString();
			}else{
				str = toString();
			}
		}
		return str;
	}
	
}


