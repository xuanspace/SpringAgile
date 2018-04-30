package com.agile.framework.validate;

import com.agile.framework.utils.ReflectUtils;

public class FieldConstraint {

	// 表ID
	private int tableId;

	// 表名
    private String tableName;
	
	// 字段名
    private String filedName;

    // 字段约束
    private String constraint;

    // 字段约束信息
    private String message;

    public int getTableId() {
		return tableId;
	}

	/*
    Bean Validation 中内置的 constraint
	@Null 被注释的元素必须为 null
	@NotNull 被注释的元素必须不为 null
	@AssertTrue 被注释的元素必须为 true
	@AssertFalse 被注释的元素必须为 false
	@Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
	@Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
	@DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
	@DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
	@Size(max=, min=) 被注释的元素的大小必须在指定的范围内
	@Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
	@Past 被注释的元素必须是一个过去的日期
	@Future 被注释的元素必须是一个将来的日期
	@Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
	
	Hibernate Validator 附加的 constraint
	@NotBlank(message =) 验证字符串非null，且长度必须大于0
	@Email 被注释的元素必须是电子邮箱地址
	@Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
	@NotEmpty 被注释的字符串的必须非空
	@Range(min=,max=,message=) 被注释的元素必须在合适的范围内     
	 */
    
    /**
     * 对字段进行校验
     * @param object 实体对象
     * @return 返回校验结果
     */	
	public boolean validate(Object object) {
		try {
			Object value = ReflectUtils.getFieldValue(object, filedName);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
    
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
