package com.agile.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.agile.framework.utils.ReflectUtils;
import com.agile.framework.validate.AbstractValidator;
import com.agile.model.Constraint;
import com.agile.service.interfaces.ConstraintService;

public class BaseValidator<T> extends AbstractValidator<T> {

	@Autowired
	protected ConstraintService constraintService;
	
	private List<Constraint> constrains = null;
	
    /**
     * 获取实体类的字段名
     * @param clazz 实体类
     */	
	public List<Constraint> getEntityConstrains(Class<?> clazz) {
		if (constrains == null) {
			constrains = constraintService.getList();
		}
		return constrains;
	}
	
    /**
     * 根据数据库对实体对象进行校验
     * @param target 实体类对象
     */	
	public void validate(Object target) throws BindException {
        getEntityConstrains(target.getClass());
        String objectName = target.getClass().getSimpleName();
        BindingResult result = new BeanPropertyBindingResult(target, objectName);
        
        // 数据库约束校验
        for (Constraint constraint : constrains) {
        	if (!validate(target, constraint)) {
            	String property = constraint.getFieldName();
            	String message = constraint.getMessage();
            	result.addError(new FieldError(objectName, property, message));        		
        	}
        }
        
        // 子类自定义校验
        this.validate(target, result);
        
        // 父类缺省校验
        super.validateObject(target, result);
        if (result.hasErrors()) {
            throw new BindException(result);
        }
	}
	
    /**
     * 对字段进行校验
     * @param object 实体对象
     * @return 返回校验结果
     */	
	public boolean validate(Object object, Constraint constraint) {
		try {
			String filedName = constraint.getFieldName();
			Object value = ReflectUtils.getFieldValue(object, filedName);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
	}	
}
