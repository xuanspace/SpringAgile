package com.agile.framework.query;

import java.util.Date;

/**
 * 查询条件类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0  
 */
public class Expression {

	private Object left;
	
	private Object operator;
	
	private Object right;
	
	private String clause;
	
	public Expression() {
		this.left = null;
		this.operator = null;
		this.right = null;
		this.clause = "";
	}

    /**
     * SELECT TABLE表达式转为字符串
     * 如: build.select(DB.SYS_USER)
     * 
     * @return 表达式字符串
     */	
	public String toTableSelectString() {
		String str = right.toString();
		str += ".*";
		return str;
	}
	
    /**
     * JOIN表达式转为字符串
     * 如: build.join(SYS_USER_ROLE.USER_ID).on(SYS_USER.ID)
     * 
     * @return 表达式字符串
     */	
	public String toJoinString() {
		String str = "left join ";
		SQLField<?> field1 = (SQLField<?>)right;
		str += field1.getTable();
		str += " on ";

		SQLField<?> field2 = (SQLField<?>)left;
		str += field2.getTable();
		str += ".";
		str += field2.getName();		
		str += " = ";
		
		str += field1.getTable();
		str += ".";
		str += field1.getName();
		
		return str;
	}

    /**
     * 常规表达式转为字符串
     * 
     * @return 表达式字符串
     */	
	public String toNomalString() {
		String str = "";
		if (left != null) {
			str += left.toString();
		}
		if (operator!= null) {
			if (operator.equals(SQL.COMMA))
				str += operator.toString();
			else
				str += " " + operator.toString();
		}
		if (right != null) {
			str += getRightString();
		}
		return str;		
	}

    /**
     * 判断对象是不是String/Data类型
     * @return String/Data类型返回true
     */	
	public boolean isStringType(Object object) {
		Class<?> clazz = object.getClass();
		// 字符串对象
        if (String.class.equals(clazz))
        	return true;
        // 日期对象
        else if (Date.class.equals(clazz))
        	return true;
        return false;
	}
	
    /**
     * 表达式右边对象转为字符串
     * 
     * @return 表达式字符串
     */	
	public String getRightString() {
		String str = "";
		// AS后面的字符串不需要''
		if (operator != null && operator.equals(SQL.AS)) {
			str += " " + right.toString();
		}else if (isStringType(right)) {	
			str += " '" + right.toString() + "'";
		}else {
			str += " " + right.toString();
		}
		return str;
	}

    /**
     * 表达式转为字符串
     * 
     * @return 表达式字符串
     */	
	public String toString() {
		// JOIN表达式
		if (operator!= null) {
			if (operator.equals(SQL.LEFT_JOIN)) {
				return toJoinString();
			}else if (operator.equals(SQL.SELECT_TABLE)) {
				return toTableSelectString();				
			}
		}
		// 常规表达式
		return toNomalString();
	}
	
	public Object getLeft() {
		return left;
	}

	public void setLeft(Object left) {
		this.left = left;
	}

	public Object getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public void setOperator(SQL operator) {
		this.operator = operator;
	}

	public Object getRight() {
		return right;
	}

	public void setRight(Object right) {
		this.right = right;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}	
}
