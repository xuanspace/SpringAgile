package com.agile.framework.query;

import java.util.List;

/**
 * 表条件操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0  
 */
public class Condition {
	
	private List<Expression> expressions = null;

	public Condition() {
		
	}
	
	public Condition(Builder builder) {
		expressions = builder.getExpressions();
	}

	public Condition(Where where) {
		expressions = where.getExpressions();
	}
	
	public Condition and(String paramString) {
		return null;
	}
	
	public Condition and(Field<?> field) {
        List<Expression> exps = field.getExpressions();
        addOperator("and");
        if (exps.size() > 1) {
            addOperator("(");
            expressions.addAll(exps);
            addOperator(")");
        }else{
            expressions.addAll(exps);
        }
		return this;
	}

	@SafeVarargs
	public final Condition and(Field<?>...fields) {
		return null;
	}

	public Condition or(String paramString) {
		return null;
	}

	public Condition or(Field<?> field) {
		return null;
	}
	
	@SafeVarargs
	public final Condition or(Field<?>...fields) {
		return null;
	}
	
	public Condition orderby(Field<?> field) {
		return null;
	}

    public void addOperator(String token) {
        Expression expression = new Expression();
        expression.setOperator(token);
        expressions.add(expression);
    }
}
