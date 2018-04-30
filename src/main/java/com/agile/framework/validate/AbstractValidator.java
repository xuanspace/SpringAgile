package com.agile.framework.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.*;

import com.agile.framework.controller.AbstractDaoController;
import com.agile.framework.service.IDaoService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

/*
 * 校验基础类
 *   1. 获取实体类字段约束注解进行校验.
 *   2. 很多时候实体类是代码生成器生成无法添加字段约束注解，那么提供自定义constrain函数设置约束. 
 *   3. 另外一种解决方式是通过系统配置实体类字段校验 
 *   
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0
 */
public abstract class AbstractValidator<T> implements Validator {

	public static final Logger logger = LoggerFactory.getLogger(AbstractDaoController.class.getName()); 
	
    protected Class<T> typeClass = null;
    protected EntityAssist entityAssist = null;
    protected javax.validation.Validator defaultValidator = null;

    /**
     * 构造函数
     */
    public AbstractValidator() {
        getEntityClass();
        getEntityAssist();
    }

    /**
     * 返回参数T的类型
     *
     * @return the entity class
     *
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        if (typeClass == null) {
            typeClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return typeClass;
    }

    /**
     * 返回参数T的类注解助手
     * @return the entity assist
     */
    public EntityAssist getEntityAssist() {
        if (entityAssist == null) {
            entityAssist = new EntityAssist(getEntityClass());
        }
        return entityAssist;
    }

    /**
     * 获取缺省的Validator
     *
     * @return Validator
     *
     */
    public javax.validation.Validator getDefaultValidator() {
        if (defaultValidator == null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            defaultValidator = factory.getValidator();
        }
        return defaultValidator;
    }
    
    /**
     * 根据实体成员的注解进行对象校验
     *
     * @param target 
     * @throws 抛出BindException异常
     *
     */
    public void validateObject(Object target, BindingResult result) {
        // 缺省校验处理
    	String objectName = target.getClass().getSimpleName();
        Set<?> violations = getDefaultValidator().validate(target);
        for (Object item: violations) {
            ConstraintViolation<?> violation = (ConstraintViolation<?>)item;
            String propertyPath = violation.getPropertyPath().toString(); //对象属性
            String message = violation.getMessage(); //错误信息
            result.addError(new FieldError(objectName, propertyPath, message));
        }
    }

    /**
     * 实体T对象校验
     *
     * @request
     * @return the entity
     *
     */
    public void validate(Object target) throws BindException {
        
    }

    /**
     * 设置实体类校验注解
     */
    public void constrain() {
        //entityAssist.setNotNull("name", "不能为空");
    }
}
