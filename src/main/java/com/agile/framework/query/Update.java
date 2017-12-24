package com.agile.framework.query;

import java.util.List;

/**
 * 表Update操作表达式类
 *  T 表字段类型
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0     
 */
public class Update {

	private Builder builder = null;
	private List<Expression> expressions = null;


	public Update() {
	}

	public Update(Builder builder) {
		this.builder = builder;
		this.expressions = builder.getExpressions();
	}

	public <T> Update set(Field<T> field, T value) {
		Expression expression = new Expression();
		expression.setOperator("set");
		expressions.add(expression);

		expression = new Expression();
		expression.setLeft(field);
		expression.setOperator("=");
		expression.setRight(value);
		expressions.add(expression);
		return this;
	}

	public <T> Where where(Field<T> field) {
		Expression expression = new Expression();
		expression.setOperator("where");
		expressions.add(expression);
		List<Expression> exps = field.getExpressions();
		expressions.addAll(exps);
		Where where = new Where(builder);
		return where;
	}
	
}
