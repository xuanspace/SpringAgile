package com.agile.framework.query;

import java.util.List;

/**
 * 表条件操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0  
 */
public class ConditionStatement extends Statement {
	
	public ConditionStatement(Builder builder) {
		expressions = builder.getExpressions();
	}

	public ConditionStatement(WhereStatement where) {
		expressions = where.getExpressions();
	}
	
	public ConditionStatement and(String paramString) {
		return null;
	}
	
	public ConditionStatement and(SQLField<?> field) {
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

	public ConditionStatement and(SQLField<?>...fields) {
		return null;
	}

	public ConditionStatement or(String paramString) {
		return null;
	}

	public ConditionStatement or(SQLField<?>...fields) {
		return null;
	}
	
	public ConditionStatement orderby(SQLField<?> field) {
		return null;
	}
}
