package com.agile.framework.query;

import java.util.ArrayList;
import java.util.List;

import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.utils.EntityUtils;

public class SQLBuilder extends Statement {

	// Select类型
	protected final static char S = 'S';

	// Update类型
	protected final static char U = 'U';

	// Delete类型
	protected final static char D = 'D';
	
	// From类型
	protected final static char F = 'F';
	
	// Where类型
	protected final static char W = 'W';

	// Normal类型
	protected final static char N = 'N';
	
	// Select表达式
	protected List<Expression> selectExpressions = null;

	// From表达式
	protected List<Expression> fromExpressions = null;
	
	// Where表达式
	protected List<Expression> whereExpressions = null;

	// 实体类
	protected Class<?> entityClass = null;

	// 结果类
	protected Class<?> resultClass = null;
	
	// 实体类DAO
	protected IBaseDao<?> baseDao = null;

	// 语句参数
	protected List<ParameterBinding> parameters = null;
	
	public void creaeteExpressions() {
		expressions = new ArrayList<Expression>();
		selectExpressions = new ArrayList<Expression>();
		fromExpressions = new ArrayList<Expression>();		
		whereExpressions = new ArrayList<Expression>();
		parameters = new ArrayList<ParameterBinding>();
	}

    //
    // 表达式转字符串  
    //
	public String getDefaultTableName() {
		String str = "";
		if (entityClass != null) {
			String tableName = EntityUtils.getTableName(entityClass);
			if (tableName != null)
				str += " " + tableName; 
		}
		return str;
	}

	public String getSelectString() {
		String str = getExpressionsString(selectExpressions);
		if (selectExpressions.size() < 1 ) {
			str = "select *";
		}
		return str;
	}

	public String getFromString() {
		String str = "";
		Expression exp = null;
		if (fromExpressions.size() > 0)
			exp = fromExpressions.get(0);
		if (exp == null || exp.getOperator() != SQL.FROM)
			str = " from" + getDefaultTableName();
		str += getExpressionsString(fromExpressions);
		return str;
	}

	public String getWhereString() {
		String str = getExpressionsString(whereExpressions);
		if (fromExpressions.size() < 1 ) {
			str = " where 1";
		}
		return str;
	}
	
    public String getExpressionsString(List<Expression> exps) {
		String str = "";
		for (Expression exp: exps) {
			String statment = exp.toString();
			if (statment.startsWith(" ") || statment.startsWith(","))
				str += statment;
			else
				str += " " + statment;
		}
		return str;
	}
   
    //
    // 类型语句添加  
    // 	
	public Expression addClause(Expression exp, char type) {
		if (exp == null)
			return exp;
		if (type == S)
			selectExpressions.add(exp);
		else if (type == F)
			fromExpressions.add(exp);
		else if (type == W)
			whereExpressions.add(exp);
		else
			expressions.add(exp);
		return exp;
	}

	public void addAllClause(List<Expression> exps, char type) {
		if (exps == null)
			return;
		if (type == S)
			selectExpressions.addAll(exps);
		else if (type == F)
			fromExpressions.addAll(exps);
		else if (type == W)
			whereExpressions.addAll(exps);
		else
			expressions.addAll(exps);
	}

    //
    // 通用语句类型  
    // 	
	public void add(Expression exp, char type) {
		addClause(exp, type);
	}
	
	public void add(List<Expression> exps, char type) {
		addAllClause(exps, type);		
	}
	
    public Expression addLeft(Object object, char type) {
        Expression exp = new Expression();
        exp.setLeft(object);
		addClause(exp, type);
		return exp;
    }
	
    public Expression addOperator(String token, char type) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, type);
    	return exp;
    }
	
    public Expression addOperator(SQL token, char type) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, type);
    	return exp;
    }
    
    public Expression addRight(Object object, char type) {
        Expression exp = new Expression();
        exp.setRight(object);
    	addClause(exp, type);
    	return exp;
    }
	
    //
    // SELECT语句
    //   
	public void addSelect(Expression exp) {
		addClause(exp, S);
	}
	
	public void addSelect(List<Expression> exps) {
		addAllClause(exps, S);
	}
	
    public Expression addSelectLeft(Object object) {
        Expression exp = new Expression();
        exp.setLeft(object);
		addClause(exp, S);
		return exp;
    }
	
    public Expression addSelectOperator(String token) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, S);
    	return exp;
    }
	
    public Expression addSelectOperator(SQL token) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, S);
    	return exp;
    }
    
    public Expression addSelectRight(Object object) {
        Expression exp = new Expression();
        exp.setRight(object);
    	addClause(exp, S);
    	return exp;
    }

    //
    // From语句
    //
	public void addFrom(Expression exp) {
		addClause(exp, F);
	}
	
	public void addFrom(List<Expression> exps) {
		addAllClause(exps, F);
	}
	
    public Expression addFromLeft(Object object) {
        Expression exp = new Expression();
        exp.setLeft(object);
		addClause(exp, F);
		return exp;
    }
	
    public Expression addFromOperator(String token) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, F);
    	return exp;
    }
	
    public Expression addFromOperator(SQL token) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, F);
    	return exp;
    }
    
    public Expression addFromRight(Object object) {
        Expression exp = new Expression();
        exp.setRight(object);
    	addClause(exp, F);
    	return exp;
    }

    //
    // WHERE语句
    //   
	public void addWhere(Expression exp) {
		addClause(exp, W);
	}
	
	public void addWhere(List<Expression> exps) {
		addAllClause(exps, W);
	}
	
    public Expression addWhereLeft(Object object) {
        Expression exp = new Expression();
        exp.setLeft(object);
		addClause(exp, W);
		return exp;
    }
	
    public Expression addWhereOperator(String token) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, W);
    	return exp;
    }
	
    public Expression addWhereOperator(SQL token) {
        Expression exp = new Expression();
        exp.setOperator(token);
    	addClause(exp, W);
    	return exp;
    }
    
    public Expression addWhereRight(Object object) {
        Expression exp = new Expression();
        exp.setRight(object);
    	addClause(exp, W);
    	return exp;
    }
    
    //
    // Getter/Setter
    //    
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

	public Class<?> getResultClass() {
		return resultClass;
	}

	public void setResultClass(Class<?> resultClass) {
		this.resultClass = resultClass;
	}
	
	public List<Expression> getSelectExpressions() {
		return selectExpressions;
	}

	public void setSelectExpressions(List<Expression> selectExpressions) {
		this.selectExpressions = selectExpressions;
	}

	public List<Expression> getFromExpressions() {
		return fromExpressions;
	}

	public void setFromExpressions(List<Expression> fromExpressions) {
		this.fromExpressions = fromExpressions;
	}

	public List<Expression> getWhereExpressions() {
		return whereExpressions;
	}

	public void setWhereExpressions(List<Expression> whereExpressions) {
		this.whereExpressions = whereExpressions;
	}
	
}
