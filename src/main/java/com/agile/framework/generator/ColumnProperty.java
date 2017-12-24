/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.framework.generator;

public class ColumnProperty {
	// 表字段描述	
	public String name;
	public int sqlType;	
	public int sqlColumnSize;
	public int sqlDecimalDigits;
	public boolean sqlNotNull;
	public boolean sqlReadOnly;
	public String sqlTypeName;
	public String defalutValue;
	public String remarks;
	public boolean primaryKey;
	public boolean autoIncrement;
	
	// Sql类型对应Java类型
	public Class<?> javaType;
	
	// 字段名转成Java规范的命名
	public String javaName;
	
	/* 
	 * 通过getColumns()方法返回的结果集中的每一列包含23个字段的描述信息
	 * 
	1.  TABLE_CAT String => table catalog (may be null)
	2.  TABLE_SCHEM String => table schema (may be null)
	3.  TABLE_NAME String => table name (表名称)
	4.  COLUMN_NAME String => column name(列名)
	5.  DATA_TYPE int => SQL type from java.sql.Types(列的数据类型)
	6.  TYPE_NAME String => Data source dependent type name, for a UDT the type name is fully qualified
	7.  COLUMN_SIZE int => column size.
	8.  BUFFER_LENGTH is not used.
	9.  DECIMAL_DIGITS int => the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
	10. NUM_PREC_RADIX int => Radix (typically either 10 or 2)
	11. NULLABLE int => is NULL allowed.
	12. REMARKS String => comment describing column (may be null)
	13. COLUMN_DEF String => default value for the column, (may be null)
	14. SQL_DATA_TYPE int => unused
	15. SQL_DATETIME_SUB int => unused
	16. CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
	17. ORDINAL_POSITION int => index of column in table (starting at 1)
	18. IS_NULLABLE String => ISO rules are used to determine the nullability for a column.
	19. SCOPE_CATLOG String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
	20. SCOPE_SCHEMA String => schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
	21. SCOPE_TABLE String => table name that this the scope of a reference attribure (null if the DATA_TYPE isn't REF)
	22. SOURCE_DATA_TYPE short => source type of a distinct type or user-generated Ref type, SQL type from java.sql.Types
	23. IS_AUTOINCREMENT String => Indicates whether this column is auto incremented	
	*/
	
	public ColumnProperty() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	public int getSqlColumnSize() {
		return sqlColumnSize;
	}

	public void setSqlColumnSize(int sqlColumnSize) {
		this.sqlColumnSize = sqlColumnSize;
	}

	public int getSqlDecimalDigits() {
		return sqlDecimalDigits;
	}

	public void setSqlDecimalDigits(int sqlDecimalDigits) {
		this.sqlDecimalDigits = sqlDecimalDigits;
	}

	public boolean isSqlNotNull() {
		return sqlNotNull;
	}

	public void setSqlNotNull(boolean sqlNotNull) {
		this.sqlNotNull = sqlNotNull;
	}

	public boolean isSqlReadOnly() {
		return sqlReadOnly;
	}

	public void setSqlReadOnly(boolean sqlReadOnly) {
		this.sqlReadOnly = sqlReadOnly;
	}

	public String getSqlTypeName() {
		return sqlTypeName;
	}

	public void setSqlTypeName(String sqlTypeName) {
		this.sqlTypeName = sqlTypeName;
	}

	public String getDefalutValue() {
		return defalutValue;
	}

	public void setDefalutValue(String defalutValue) {
		this.defalutValue = defalutValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Class<?> getJavaType() {
		return javaType;
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	
	public Boolean getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(Boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}	
}