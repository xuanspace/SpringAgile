package com.agile.framework.query;

import java.util.List;

/**
 * 表Where操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0    
 */
public class Where {

	private Builder builder = null;
	private List<Expression> expressions = null;

	public Where() {
	}
	
	public Where(Builder builder) {
		this.builder = builder;
        this.expressions = builder.getExpressions();
	}
	
	public Builder where(Field<?> field) {
        Expression expression = new Expression();
        expression.setOperator("where");
        expressions.add(expression);

		List<Expression> exps = field.getExpressions();
		expressions.addAll(exps);
		return builder;
	}

	public Condition where(Condition...conditions) {
		Condition condition = new Condition(this);
		return condition;
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

}
