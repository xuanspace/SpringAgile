package com.agile.framework.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 表操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0   
 */
public class Table {

    private List<Expression> expressions = null;

    public Table() {

    }
    private Table(Table me) {
        this.expressions = new ArrayList<Expression>();
    }

	public String getName() {
	    return null;
    }
	
	public String getComment() {
	    return null;
    }
	
	public Table as(String alias) {
        Table table = this.copy();
        Expression expression = new Expression();
        expression.setLeft(this);
        expression.setOperator("as");
        expression.setRight(alias);
        table.addExpression(expression);
        return table;
	}

    public Table copy() {
        Table field = new Table(this);
        return field;
    }

    public void addExpression(Expression exps) {
        expressions.add(exps);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

}
