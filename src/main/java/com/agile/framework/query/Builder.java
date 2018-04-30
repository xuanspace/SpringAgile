package com.agile.framework.query;

import java.util.*;
import com.agile.framework.persistence.IBaseDao;

/**
 * 操作表达式类
 *  SELECT
 *   [ALL | DISTINCT | DISTINCTROW ]
 *   select_expr [, select_expr ...]
 *   [FROM table_references
 *     [PARTITION partition_list]
 *   [WHERE where_condition]
 *   [GROUP BY {col_name | expr | position}
 *     [ASC | DESC], ... [WITH ROLLUP]]
 *   [HAVING where_condition]
 *   [ORDER BY {col_name | expr | position}
 *     [ASC | DESC], ...]
 *   [LIMIT [offset]]
 *    
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0 
 */
public class Builder extends SQLBuilder {
	
	private Boolean isSelect = false;

	private Boolean isFrom = false;
	
	private Boolean isWhere = false;

	private Boolean isUpdate = false;
	
	private Boolean isDelete = false;

	private Boolean isPageable = true;
	
	private Boolean isPositionParameter = false;
	
	private Boolean isNameParameter = false;
	
	private Integer limit = null;
	
	private Integer offset = null;
	
    private String hql = null;

	private String sql = null;
	
    /**
     * 缺省构造函数
     */		
	public Builder() {
		creaeteExpressions();
	}

    /**
     * 构造函数
     * @param clazz 实体类Class
     */		
	public Builder(Class<?> clazz) {
		creaeteExpressions();
		this.entityClass = clazz;		
	}

    /**
     * 构造函数
     * @param dao 实体类对象
     */		
	public Builder(IBaseDao<?> dao) {
		creaeteExpressions();
		this.baseDao = dao;
		if (dao != null) {
			this.entityClass = dao.getEntityClass();
		}		
	}

    /**
     * Select多个表字段名
     * 
     * @param fields 多个字段名
     * @return 返回from语句对象
     */		
	public FromStatement select(String field) {
		Expression expression = new Expression();
		FromStatement from = new FromStatement(this);
		if (isSelect == false) {
			expression.setOperator(SQL.SELECT);
			isSelect = true;
		}else{
			addSelectOperator(SQL.COMMA);
		}
		// 第二次不用添加SELECT
		expression.setRight(field);
		addSelect(expression);
		return from;
	}

    /**
     * Select多个表字段
     * 
     * @param fields 多个字段对象
     * @return 返回from语句对象
     */		
	public FromStatement select(SQLField<?>... fields) {
		FromStatement from = new FromStatement(this);
		if (isSelect == false) {
			addSelectOperator(SQL.SELECT);
			isSelect = true;
		}else{
			addSelectOperator(SQL.COMMA);
		}
		
        for(int i = 0; i < fields.length; i++) {
        	List<Expression> exps = fields[i].getExpressions();
        	if (exps == null) // 单纯字段
        		addSelectRight(fields[i]);
        	else
        		addSelect(exps); // 字段有表达式
        }
		return from;
	}

    /**
     * Select整个表字段
     * 
     * @param tables 表对象
     * @return 返回from语句对象
     */	
	public WhereStatement select(SQLTable... tables) {
		Expression expression = null;				
		WhereStatement where = new WhereStatement(this);
		if (isSelect == false) {
			addSelectOperator(SQL.SELECT);
			isSelect = true;
		} else {
			addSelectOperator(SQL.COMMA);
		}
		
        for(int i = 0; i < tables.length; i++) {
        	List<Expression> exps = tables[i].getExpressions();
        	if (exps == null) { // 单纯表
        		expression = new Expression();
        		expression.setOperator(SQL.SELECT_TABLE);
        		expression.setRight(tables[i]);
        		addSelect(expression);
        		if (i < tables.length-1) {
        			addSelectOperator(SQL.COMMA);
        		}
        	}else {
        		addSelect(exps); // 表有表达式
        	}
        }
		return where;
	}

    /**
     * From语句
     * 
     * @param fields 表名
     * @return 返回where语句对象
     */		
	public WhereStatement from(String table) {
    	Expression expression = new Expression();
    	WhereStatement where = new WhereStatement(this);
		if (isFrom == false) {
			expression.setOperator(SQL.FROM);
			isFrom = true;
		}else {
			addFromOperator(SQL.COMMA);
		}
    	expression.setRight(table);
    	addFrom(expression);
		return where;
	}

    /**
     * From语句
     * 
     * @param table 表对象
     * @return 返回where语句对象
     */		
	public WhereStatement from(SQLTable table) {
    	WhereStatement where = new WhereStatement(this);
		if (isFrom == false) {
			addFromOperator(SQL.FROM);
			isFrom = true;
		}else {
			addFromOperator(SQL.COMMA);
		}    	
		// 表是否有表达式
    	List<Expression> exps = table.getExpressions();
    	if (exps == null)
    		addFromRight(table);
    	else
    		addFrom(exps);
		return where;
	}

    /**
     * Update语句
     * 
     * @param table 表对象
     * @return 返回update语句对象
     */		
	public UpdateStatement update(SQLTable table) {
		Expression expression = new Expression();
		UpdateStatement update = new UpdateStatement(this);
		if (isUpdate == false) {
			isUpdate = true;
		}
		expression.setOperator(SQL.UPDATE);
		expression.setRight(table);
		add(expression);		
		return update;
	}

    /**
     * Delete语句
     * 
     * @param table 表对象
     * @return 返回delete语句对象
     */		
	public WhereStatement delete(SQLTable table) {
		Expression expression = new Expression();
		WhereStatement where = new WhereStatement (this);
		if (isDelete == false) {
			isDelete = true;
		}
        expression.setOperator(SQL.DELETE);
		expression.setRight(table);
		add(expression);		
		return where ;
	}

	/**
	 * Where条件语句
	 *  如果删除和更新的where直接放入常规表达式
	 * @param field 字段对象
	 * @return 返回builder自己
	 */	
	public Builder where(SQLField<?> field) {
		if (isUpdate || isDelete) {
			if (isWhere == false) {
				addOperator(SQL.WHERE);
				isWhere = true;
			}else {
				addOperator(SQL.COMMA);
			}
			add(field.getExpressions());
		} else {
			if (isWhere == false) {
				addWhereOperator(SQL.WHERE);
				isWhere = true;
			}else {
				addWhereOperator(SQL.COMMA);
			}
			addWhere(field.getExpressions());
		}
		return this;
	}

	/**
	 * Join语句
	 * @param field 字段对象
	 * @return 返回join语句对象
	 */	
	public JoinStatement join(SQLField<?> field) {        
        JoinStatement joinStatement = new JoinStatement(this);
        Expression expression = new Expression();
        expression.setLeft(field);
        expression.setOperator(SQL.LEFT_JOIN);
        addFrom(expression);
        addFrom(field.getExpressions());
		return joinStatement;
	}
	
	public Builder limit(int size) {
		Expression expression = new Expression();
		expression.setOperator(SQL.LIMIT);
		expression.setRight(size);
		this.add(expression);
		this.limit = size;
		return this;
	}

	public Builder offset(int value) {
		Expression expression = new Expression();
		expression.setOperator(SQL.OFFSET);
		expression.setRight(value);
		this.add(expression);
		this.offset = value;
		return this;
	}

	public Builder order(SQLField<?> field) {
		Expression expression = new Expression();
		expression.setOperator(SQL.ORDER_BY);
		expression.setRight(field);
		this.add(expression);
		return this;
	}

	public List<?> list() {
		return this.baseDao.query(this);
	}

	public long execute() {
		return this.baseDao.execute(this);
	}
	
	public Builder sql(String sql) {
		this.sql = sql;
		return this;
	}

	public Builder hql(String hql) {
		this.hql = hql;
		return this;
	}

	public boolean isSql() {
		return (this.sql != null) ? true : false;
	}

	public boolean isHql() {
		return (this.hql != null) ? true : false;
	}

    /**
     * 是否允许分页
     *   
     * @param pageable 是否在语句中分页
     */		
	public void setPageable(boolean pageable) {
		isPageable = pageable;
	}

    /**
     * 设置语句中的参数
     *   如 select :name, :age from user where 1
     *   
     * @param name 参数名
     * @param value 参数值
     * @throws BuilderException 
     */		
	public void setParameter(String name, Object value) {
		ParameterBinding bind = new ParameterBinding();
		bind.setName(name);
		bind.setValue(value);
		parameters.add(bind);
		isNameParameter = true;
	}

    /**
     * 设置语句中的参数
     *   如 select ?, age from user where 1
     *   
     * @param index 第几个参数
     * @param value 参数值
     */		
	public void setParameter(int position, Object value) {
		ParameterBinding bind = new ParameterBinding();
		bind.setPosition(position);
		bind.setValue(value);
		parameters.add(bind);
		isPositionParameter = true;
	}

	public String getParameter(int position) {
		if (position < parameters.size())
			return parameters.get(position).toString();
		return "?";
	}

	public String getParameter(String name) {
		for (ParameterBinding bind : parameters) {
			if (bind.getName().equals(name))
				return bind.toString(); 
		}
		return "";
	}
	
    /**
     * 获取参数替换到语句字符串
     *   
     * @param String  
     */		
	public String fillSqlString() {
		StringBuilder str = new StringBuilder(sql.length());
		int position = 0;
		for(int i=0; i<sql.length(); i++) {
			char c = sql.charAt(i);
			if (c == '?') {				
				str.append(getParameter(position++));
			}
			else if (c == ':') {
				for(++i; i<sql.length(); i++)
					if (sql.charAt(i) != ' ')
						break;
				position = i;
				for(;i<sql.length();i++) {
					c = sql.charAt(i);
					if (c == ' ' || c == ',') {
						String name = sql.substring(position, i--);						
						str.append(getParameter(name));
						break;
					}
				}
			} else {
				str.append(c);
			}
		}
		return str.toString();
	}
	
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
    public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public boolean isSelect() {
		return (isUpdate || isDelete) ? false : true;
	}
	
	/**
	 * 将表达式转成sql语句
	 * 
	 * @return  返回sql语句
	 */		
	public String toString() {
		String str = "";
		
		// 如果直接设置sql带参数
		if (sql != null || hql != null) {
			if (isPositionParameter || isNameParameter) {
				return fillSqlString();
			}
		}		
		
		// 如果查询没有select/from则添加
		if (isSelect()) {
			str += getSelectString();
			str += getFromString();
			str += getWhereString();
		}
		
		// 遍历表达式转成字符串
		if (expressions == null || expressions.size() < 1)
			return str;		
		for (Expression exp: expressions) {
			// 如果不允许分页跳过limit/offset
			if (isPageable == false) {
				if (exp.getOperator().equals("limit") || exp.getOperator().equals("offset"))
					continue;
			}
			String statment = exp.toString();
			if (statment.startsWith(" ") || statment.startsWith(","))
				str += statment;
			else
				str += " " + statment;
		}
		
		// 去掉最前面的空格
		if (str.startsWith(" "))
			str = str.trim();
		return str;
	}

	/**
	 * 转成不分页的SQL语句
	 * @return  返回sql语句
	 */	
	public String toNoPageSql() {
		isPageable = false;
		return toString();
	}

	/**
	 * 转成不分页的HQL语句
	 * @return  返回sql语句
	 */	
	public String toNoPageHql() {
		isPageable = false;
		return toHql();
	}
	
	/**
	 * 转成SQL语句
	 * @return  返回sql语句
	 */	
	public String toSql() {
		return toString();
	}

	/**
	 * 转成HQL语句
	 * @return  返回hql语句
	 */
	public String toHql() {
		String str = "";
		// 如果设置hql语句则返回
		if (getHql() != null)
			str = getHql();
		// 通过拼接返回hql语句
		else
			str = toString();
		return str;
	}
	
}


