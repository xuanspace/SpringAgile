package com.agile.framework.query;

/**
 * 表Where操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0    
 */
public class WhereStatement extends Statement {

	private Builder builder = null;
	
	public WhereStatement(Builder builder) {
		this.builder = builder;
        this.expressions = builder.getExpressions();
	}
	
	public Builder where(SQLField<?> field) {
        Expression expression = new Expression();
        expression.setOperator(SQL.WHERE);
        add(expression);
        add(field.getExpressions());
		return builder;
	}

	public ConditionStatement where(ConditionStatement...conditions) {
		ConditionStatement condition = new ConditionStatement(this);
		return condition;
	}
}
