package com.agile.framework.validate;

import com.agile.framework.utils.EntityUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Set;

/*
 * 校验基础类
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0
 */
public abstract class AbstractValidator<T> implements Validator {

    protected Class<T> typeClass;
    protected EntityAssist entityAssist;
    protected javax.validation.Validator defaultValidator;

    /**
     * 构造函数
     */
    public AbstractValidator() {
        getEntityClass();
        getEntityAssist();
        getDefaultValidator();
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
     * 实体对象校验
     *
     * @request
     * @return the entity
     *
     */
    public void validateObject(Object target) throws Exception {
        String objectName = target.getClass().getSimpleName();
        BindingResult result = new BeanPropertyBindingResult(target, objectName);

        // 缺省校验处理
        Set<?> violations = defaultValidator.validate(target);
        for (Object item: violations) {
            ConstraintViolation<?> violation = (ConstraintViolation<?>)item;
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            result.addError(new FieldError(objectName, propertyPath, message));
        }

        // 自定义校验处理
        this.validate(target, result);
        if (result.hasErrors()) {
            throw new BindException(result);
        }
    }

    /**
     * 实体T对象校验
     *
     * @request
     * @return the entity
     *
     */
    public void validateEntity(T target) throws Exception {
        validateObject(target);
    }

    /**
     * 实体对象校验
     *
     * @request
     * @return the entity
     *
     */
    public void validateEntity(T target, BindingResult result) throws Exception {
        // 自定义校验处理
        this.validate(target, result);
        if (result.hasErrors()) {
            throw new BindException(result);
        }
    }

    /**
     * 设置实体类校验注解
     */
    public void constrain() {
        //assist.setNotNull("name", "test");
    }
}
