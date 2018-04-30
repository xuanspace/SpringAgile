package com.agile.framework.query;

public class JoinStatement extends Statement {

	private Builder builder = null;
	
	public JoinStatement(Builder builder) {
		this.builder = builder;
		this.expressions = builder.getFromExpressions();
	}
	
	public Builder on(SQLField<?> field) {
        Expression expression = getLastExpression();
        expression.setRight(field);
		return builder;
	}
}
