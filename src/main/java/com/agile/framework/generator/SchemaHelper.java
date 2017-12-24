package com.agile.framework.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.agile.framework.utils.StringUtils;

public class SchemaHelper {
 
    private Connection conn;
      
	public SchemaHelper() {
		conn = JdbcHelper.getConnection();
	}

    /*** 
     * 获取数据库表名 
     * @return 表名列表
     * @throws SQLException 
     */  
	public List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();		
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
        
        while(rs.next()) {
        	String name = rs.getString("TABLE_NAME");
        	tables.add(name);
        	System.out.println("Table: " + name);
        }
        return tables;
    }    
   
    /*** 
     * 获取数据库表字段
     * @return 字段列表
     * @throws SQLException 
     */ 	
	public List<ColumnProperty> getColumns(String tableName) throws SQLException {		
		List<ColumnProperty> table = new ArrayList<ColumnProperty>();
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getColumns(null, null, tableName, null);
        
        System.out.println("Table: " + tableName);
        System.out.println("-------------------");
        String primaryKey = getPrimaryKeyName(tableName);
        
        while(rs.next()) {
        	ColumnProperty column = new ColumnProperty(); 
        	column.name = rs.getString("COLUMN_NAME");
        	column.sqlType = rs.getInt("DATA_TYPE");        	        	        	
        	column.sqlTypeName = rs.getString("TYPE_NAME");
        	column.defalutValue = rs.getString("COLUMN_DEF");
        	column.sqlColumnSize = rs.getInt("COLUMN_SIZE");
        	column.sqlDecimalDigits = rs.getInt("DECIMAL_DIGITS");
        	column.sqlNotNull = ("NO".equals(rs.getString("IS_NULLABLE")));
        	column.autoIncrement = ("YES".equals(rs.getString("IS_AUTOINCREMENT")));
        	column.remarks = rs.getString("REMARKS");        	

        	// 判断字段是否主键
        	column.primaryKey = false;
        	if (column.name.equals(primaryKey))
        		column.primaryKey = true;
        	
        	// SQL类型转JAVA类型
        	column.javaType = getJavaType(
        			column.sqlType,
        			column.sqlColumnSize,
        			column.sqlDecimalDigits
			);
        	
        	// 字段名转成规范命名
        	column.javaName = StringUtils.toCamelCase(column.name);
        	
        	table.add(column);
        	System.out.println("Column: " + column.name + " " 
        			+ column.sqlTypeName + column.sqlType + "->" + column.javaType.toString());
        }
        return table;
	}

    /*** 
     * 获取数据库表主键(多数情况先一个表一个主键)
     * @return 主键的字段名
     * @throws SQLException 
     */ 	
	public String getPrimaryKeyName(String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getPrimaryKeys(null, null, tableName);
        
        String columnName = "";
        while(rs.next()) {
        	columnName = rs.getString("COLUMN_NAME");
        	break;
        }
        System.out.println("Primary Key: " + columnName);
        return columnName;
	}
	
    /*** 
     * SQL类型转JAVA类型
		BIT             =  -7;
        TINY       		=  -6;
        SMALL       	=   5;
        INTEGER         =   4;
        BIG         	=  -5;
        FLOAT           =   6;
        REAL            =   7;
        DOUBLE          =   8;
        NUMERIC         =   2;
        DECIMAL         =   3;
        CHAR            =   1;
        VARCHAR         =  12;
        LONGVARCHAR     =  -1;
        DATE            =  91;
        TIME            =  92;
        TIMESTAMP       =  93;
        BINARY          =  -2;
        VARBINARY       =  -3;
        LONGVARBINARY   =  -4;
        NULL            =   0;
        OTHER           = 1111;
        JAVA_OBJECT     = 2000;
        DISTINCT        = 2001;
        STRUCT          = 2002;
        ARRAY           = 2003;
        BLOB            = 2004;
        CLOB            = 2005;
        REF             = 2006;
		DATALINK 		= 70;
		BOOLEAN 		= 16;

		// JDBC 4.0 
		ROWID 			= -8;
		NCHAR 			= -15;
		NVARCHAR 		= -9;
		LONGNVARCHAR 	= -16;
		NCLOB 			= 2011;
		SQLXML 			= 2009;
  
     * @return Java类型
     */ 	
	public static Class<?> getJavaType(int sqlType, int columnSize, int decimalDigits) {
		// 字符类型
		Class<?> clazz = String.class;		
		if (sqlType == Types.CHAR) {
			clazz = String.class;
		}
		else if (sqlType == Types.VARCHAR) {
			clazz = String.class;
		}
		else if (sqlType == Types.LONGVARCHAR) {
			clazz = String.class;
		}
		else if (sqlType == Types.DATE) {
			clazz = java.util.Date.class;
			//clazz = String.class;
		}
		// 日期和时间类型
		else if (sqlType == Types.TIME) {
			clazz = java.util.Date.class;
			//clazz = String.class;
		}		
		else if (sqlType == Types.TIMESTAMP) {
			clazz = java.util.Date.class;
			//clazz = String.class;
		}
		else if (sqlType == Types.BOOLEAN) {
			clazz = Boolean.class;
		}
		// 整数类型
		else if (sqlType == Types.BIT) {
			//clazz = Boolean.class;
			clazz = Integer.class;
		}		
		else if (sqlType == Types.TINYINT) {
			//clazz = Byte.class;
			clazz = Short.class;
		}
		else if (sqlType == Types.SMALLINT) {
			clazz = Short.class;
		}
		else if (sqlType == Types.INTEGER) {
			clazz = Integer.class;
		}		
		else if (sqlType == Types.BIGINT) {
			clazz = Long.class;
		}
		// 双精度浮点数值
		else if (sqlType == Types.FLOAT || sqlType == Types.REAL) {
			clazz = Float.class;
		}
		else if (sqlType == Types.DOUBLE) {
			clazz = Double.class;
		}
		else if (sqlType == Types.NUMERIC || sqlType == Types.DECIMAL) {
			if (decimalDigits == 0) {
				if (columnSize == 1) {
					//clazz = Byte.class;
					clazz=  Integer.class;
				}
				else if (columnSize < 5) {
					clazz = Short.class;
				}
				else if (columnSize < 10) {
					clazz = Integer.class;
				}
				else {
					clazz = Long.class;
				}
			}
			else {
				if (columnSize < 9) {
					clazz = Float.class;
				}
				else {
					clazz = Double.class;
				}
			}
		}
		// 二进制数据类型
		else if (sqlType == Types.BINARY) {
			clazz = Byte.class;
		}
		else if (sqlType == Types.VARBINARY) {
			clazz = Byte.class;
		}
		else if (sqlType == Types.LONGVARBINARY) {
			clazz = Byte.class;
		}
		else if (sqlType == Types.BLOB) {
			clazz = Byte.class;
		}
		else if (sqlType == Types.CLOB) {
			clazz = Byte.class;
		}
		return clazz;
	}

	
	public ColumnProperty getPrimaryKeyProperty(List<ColumnProperty> columns) {
		ColumnProperty column = null;
		for(int i=0; i<columns.size(); i++) {
			if (columns.get(i).isPrimaryKey()) {
				column = columns.get(i);
				break;
			}
		}
		return column;
	}	
}
