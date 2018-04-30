package com.agile.framework.query;

/**
 * 表Update操作表达式类
 *  T 表字段类型
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0     
 */
public class UpdateStatement extends Statement {

	private Builder builder = null;
	private boolean isSet = false;
	
	public UpdateStatement(Builder builder) {
		this.builder = builder;
		this.expressions = builder.getExpressions();
	}

	public <T> UpdateStatement set(SQLField<T> field, T value) {
		Expression expression;
		if (isSet == false) {
			expression = new Expression();
			expression.setOperator(SQL.SET);
			add(expression);
			isSet = true;
		}else {
			addOperator(SQL.COMMA);
		}
		expression = new Expression();
		expression.setLeft(field);
		expression.setOperator(SQL.EQ);
		expression.setRight(value);
		add(expression);
		return this;
	}

	public <T> WhereStatement where(SQLField<T> field) {
		Expression expression = new Expression();
		expression.setOperator(SQL.WHERE);
		add(expression);
		add(field.getExpressions());
		WhereStatement where = new WhereStatement(builder);
		return where;
	}
	
}
