package com.agile.framework.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 表字段操作表达式类
 *  T 表字段类型
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0    
 */
public class SQLField<T> extends Statement {

	private String table = null;
	
	private String name = null;
	
	public SQLField(String name) {
		this.name = name;
	}

	public SQLField(String table, String field) {
		this.table = table;
		this.name = field;
	}
	
	private SQLField(SQLField<T> me) {
		this.name = me.getName();
		this.table = me.getTable();
		this.expressions = new ArrayList<Expression>();
		if (me.expressions != null) {
			expressions.addAll(me.expressions);
		}
	}
	
	public SQLField<T> and(SQLField<T> field) {
        List<Expression> exps = field.getExpressions();
        addOperator(SQL.AND);
        if (exps.size() > 1) {
            addOperator("(");
            add(exps);
            addOperator(")");
        }else{
        	add(exps);
        }
		return this;
	}

	public SQLField<T> or(SQLField<T> field) {
		List<Expression> exps = field.getExpressions();
		addOperator(SQL.OR);
		if (exps.size() > 1) {
            addOperator("(");
            add(exps);
            addOperator(")");
        }else{
        	add(exps);
        }
    	return this;
	}

	public SQLField<T> isNull() {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.ISNULL);
    	expression.setClause("");
    	field.add(expression);
		return field;
	}

	public SQLField<T> isNotNull() {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.ISNOTNULL);
    	field.add(expression);
		return field;
	}

	public SQLField<T> eq(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.EQ);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public SQLField<T> notEq(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.NOT_EQ);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public SQLField<T> gt(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.GT);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public SQLField<T> ge(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.GE);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public SQLField<T> lt(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.LT);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public SQLField<T> le(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.LE);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public SQLField<T> like(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.LIKE);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public SQLField<T> notLike(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.NOT_LIKE);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public SQLField<T> in(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.IN);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public SQLField<T> notIn(T value) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.NOT_IN);
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public SQLField<T> as(String alias) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.AS);
    	expression.setRight(alias);
    	field.add(expression);
		return field;
	}

	public SQLField<T> asc() {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.ASC);
    	field.add(expression);
		return field;
	}

	public SQLField<T> desc() {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.DESC);
    	field.add(expression);
		return field;
	}

	public SQLField<T> between(T value1, T value2) {
		SQLField<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(SQL.BETWEEN);
    	field.add(expression);
    	expression = new Expression();
    	expression.setLeft(value1);
    	expression.setOperator(SQL.AND);
    	expression.setRight(value2);
    	field.add(expression);
		return field;
	}
	
	private SQLField<T> copy() {
		SQLField<T> field = new SQLField<T>(this);
    	return field;
	}
	
    /**
     * 字段是否为字符类型
     * @return String/Data类型返回true
     */	
	public boolean isStringType(Object object) {
		Class<?> clazz = object.getClass();
		// 字符串对象
        if (String.class.equals(clazz))
        	return true;
        // 日期对象
        else if (Date.class.equals(clazz))
        	return true;
        return false;
	}
	
	public String getTable() {
		return this.table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	public String getName() {
		return this.name;
	}

	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
