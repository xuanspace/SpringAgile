package com.agile.framework.query;

/**
 * 表From操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0   
 */
public class FromStatement extends Statement {

	private Builder builder = null;

	public FromStatement(Builder builder) {
		this.builder = builder;
		this.expressions = builder.getExpressions();
	}

	public WhereStatement from(SQLTable table) {
		WhereStatement where = new WhereStatement(builder);
		add(table.getExpressions());
		return where;
	}
	
}
