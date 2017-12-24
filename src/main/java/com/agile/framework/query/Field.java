package com.agile.framework.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 表字段操作表达式类
 *  T 表字段类型
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0    
 */
public class Field<T> implements Tuple {

	private String name = null;
	
	private List<Expression> expressions = null;

	private Field(Field<T> me) {
		this.name = me.getName();
		this.expressions = new ArrayList<Expression>();
		if (me.expressions != null) {
			expressions.addAll(me.expressions);
		}
	}
	
	public Field(String name) {
		this.name = name;
	}

	public Field<T> and(Field<T> field) {
        List<Expression> exps = field.getExpressions();
        addOperator("and");
        if (exps.size() > 1) {
            addOperator("(");
            expressions.addAll(exps);
            addOperator(")");
        }else{
            expressions.addAll(field.getExpressions());
        }
		return this;
	}

	public Field<T> or(Field<T> field) {
		List<Expression> exps = field.getExpressions();
		addOperator("or");
		if (exps.size() > 1) {
            addOperator("(");
            expressions.addAll(exps);
            addOperator(")");
        }else{
            expressions.addAll(field.getExpressions());
        }
    	return this;
	}

	public Field<T> isNull() {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("is null");
    	field.add(expression);
		return field;
	}

	public Field<T> isNotNull() {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("is not null");
    	field.add(expression);
		return field;
	}

	public Field<T> eq(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("=");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public Field<T> notEq(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("<>");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public Field<T> gt(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(">");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public Field<T> ge(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator(">=");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public Field<T> lt(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("<");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public Field<T> le(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("<=");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}
	
	public Field<T> like(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("like");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public Field<T> notLike(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("not like");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public Field<T> in(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("in");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public Field<T> notIn(T value) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("not in");
    	expression.setRight(value);
    	field.add(expression);
		return field;
	}

	public Field<T> as(String alias) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("as");
    	expression.setRight(alias);
    	field.add(expression);
		return field;
	}

	public Field<T> asc() {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("asc");
    	field.add(expression);
		return field;
	}

	public Field<T> desc() {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("desc");
    	field.add(expression);
		return field;
	}

	public Field<T> between(T value1, T value2) {
		Field<T> field = this.copy();
    	Expression expression = new Expression();
    	expression.setLeft(field);
    	expression.setOperator("between");
    	field.add(expression);
    	expression = new Expression();
    	expression.setLeft(value1);
    	expression.setOperator("and");
    	expression.setRight(value2);
    	field.add(expression);
		return field;
	}
	
	private Field<T> copy() {
		Field<T> field = new Field<T>(this);
    	return field; 
	}
	
	public void add(Expression exp) {
		this.expressions.add(exp);
	}

    public void addOperator(String token) {
        Expression expression = new Expression();
        expression.setOperator(token);
        expressions.add(expression);
    }

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public String getName() {
		return name;
	}

	public Class<T> getType() {
		return null;
	}

	public String getComment() {
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
