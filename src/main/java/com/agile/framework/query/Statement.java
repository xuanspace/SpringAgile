package com.agile.framework.query;

import java.util.List;

public class Statement {
	
	protected List<Expression> expressions = null;

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public Expression getLastExpression() {
		if (expressions != null) {
			int last = expressions.size() - 1;
			return expressions.get(last);
		}
		return null;
	}
	
	public void add(Expression exp) {
		if (exp != null) {
			expressions.add(exp);
		}
	}
	
	public void add(List<Expression> exps) {
		if (exps != null && exps.size() > 0) {
			expressions.addAll(exps);
		}
	}

    public Expression addLeft(Object object) {
        Expression expression = new Expression();
        expression.setLeft(object);
        expressions.add(expression);
        return expression;
    }
	
    public Expression addOperator(String token) {
        Expression expression = new Expression();
        expression.setOperator(token);
        expressions.add(expression);
        return expression;
    }
	
    public Expression addOperator(SQL token) {
        Expression expression = new Expression();
        expression.setOperator(token);
        expressions.add(expression);
        return expression;
    }

    public Expression addRight(Object object) {
        Expression expression = new Expression();
        expression.setRight(object);
        expressions.add(expression);
        return expression;
    }
    
    @Override
    public String toString() {
    	return "";
    }
}
