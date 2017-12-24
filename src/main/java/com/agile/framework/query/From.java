package com.agile.framework.query;

import java.util.List;

/**
 * 表From操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0   
 */
public class From {

	private Builder builder = null;
	private List<Expression> expressions = null;

	public From() {
	}

	public From(Builder builder) {
		this.builder = builder;
		this.expressions = builder.getExpressions();
	}


	public Where from(Table table) {
		Where where = new Where(builder);
		List<Expression> exps = table.getExpressions();
		expressions.addAll(exps);
		return where;
	}
	
}
