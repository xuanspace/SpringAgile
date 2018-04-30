package com.agile.framework.query;

import java.util.ArrayList;

/**
 * 表操作表达式类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0   
 */
public class SQLTable extends Statement {

	public SQLTable() {
	}
	
    public SQLTable(SQLTable me) {
        this.expressions = new ArrayList<Expression>();
    }

    /**
     * 表别名表达式 TABLE AS XXX
     * @param alias 别名
     * @return 表对象
     */
	public SQLTable as(String alias) {
        SQLTable table = this.copy();
        Expression expression = new Expression();
        expression.setLeft(this);
        expression.setOperator(SQL.AS);
        expression.setRight(alias);
        table.add(expression);
        return table;
	}

    /**
     * 拷贝表对象(重新生成一个表对象)
     * @return 表对象
     */
    public SQLTable copy() {
        SQLTable table = new SQLTable(this);
        return table;
    }
	
	public String getName() {
	    return null;
    }
	
	public String getComment() {
	    return null;
    }

	public SQLField<?>[] getFileds() {
		return null;		
	}

	@Override
	public String toString() {
	    return null;
    }
	
}
