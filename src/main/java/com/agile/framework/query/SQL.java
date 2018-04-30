package com.agile.framework.query;

/**
 * 操作表达式枚举类型
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0 
 */
public enum SQL {
	SELECT, INSERT, UPDATE, DELETE, 
	SELECT_TABLE, ALL, COMMA,
	FROM, WHERE, DISTINCT, 
	AS, ALIAS, SET,
	AND, OR, BETWEEN,
	ISNULL, ISNOTNULL, 
	EQ, NOT_EQ, GT, GE, LT, LE,
	LIKE, NOT_LIKE,
	IN, NOT_IN,
	ASC, DESC,
	LIMIT, OFFSET,
	ORDER_BY, GROUP_BY, HAVING,
	UNION, INNER_JOIN, LEFT_JOIN;
	
	@Override
	public String toString() {   
		switch (this) {
			case SELECT: return "select";
			case INSERT: return "insert";
			case UPDATE: return "update";
			case DELETE: return "delete from";
			case SELECT_TABLE: return "*";			
			case ALL: return "*";
			case COMMA: return ",";
			case FROM: return "from";
			case WHERE: return "where";
			case DISTINCT: return "distinct";
			case AS: return "as";
			case ALIAS: return "alias";
			case SET: return "set";
			case AND: return "and";
			case OR: return "or";
			case BETWEEN: return "between";
			case ISNULL: return "is null";
			case ISNOTNULL: return "is not null";
			case EQ: return "=";
			case NOT_EQ: return "<>";
			case GT: return ">";
			case GE: return "<";
			case LT: return ">=";
			case LE: return "<=";
			case LIKE: return "like";
			case NOT_LIKE: return "not like";
			case IN: return "in";
			case NOT_IN: return "not in";
			case ASC: return "asc";
			case DESC: return "desc";
			case LIMIT: return "limit";
			case OFFSET: return "offset";
			case ORDER_BY: return "order by";
			case GROUP_BY: return "group by";
			case HAVING: return "having";
			case UNION: return "union";
			case INNER_JOIN: return "inner join";
			case LEFT_JOIN: return "left join";
			default: return "unknow";   
		}
	}
}