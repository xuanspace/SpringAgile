package com.agile.framework.query;

import java.util.Date;

import static com.agile.framework.query.Words.isReservedWord;

/**
 * 查询条件类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0  
 */
public class Expression {

	private Object left;
	
	private String operator;
	
	private Object right;
	
	public Expression() {
		this.left = null;
		this.operator = null;
		this.right = null;
	}

	public Expression(Object left, String operator, Object right) {
		this.right = right;
		this.operator = operator;
		this.right = right;
	}
	
	public Object getLeft() {
		return left;
	}

	public void setLeft(Object left) {
		this.left = left;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getRight() {
		return right;
	}

	public void setRight(Object right) {
		this.right = right;
	}	

	private String getString(Object object) {
		String str = "";
		if (object instanceof Table) {
			Table table = (Table)object;
			str += " " + table.getName();
		}
		else if (object instanceof Tuple) {
			Tuple tuple = (Tuple)object;
			str += " " + tuple.toString();
		}
		else{
			Class<?> clazz = object.getClass();
	        if (String.class.equals(clazz)) {
	        	if (isReservedWord(object.toString())){
					str += " " + object;
				}
				else{
					str += " '" + object + "'";
				}
	        }
	        else if (Date.class.equals(clazz)) {
	        	Date date = (Date)object;
	        	str += " '" + date.toString() + "'";
	        }
	        else{
				str += " " + object.toString();
			}
		}
		return str;
	}
	
	public String toString() {
		String str = "";
		if (left != null) {
			str += getString(left);				
		}
		if (operator!= null) {
			str += " " + operator;
		}
		if (right != null) {
			str += getString(right);
		}
		return str;
	}
}
