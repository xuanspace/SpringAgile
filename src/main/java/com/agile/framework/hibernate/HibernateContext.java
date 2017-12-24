package com.agile.framework.hibernate;
  
import java.util.Iterator;
import java.util.Map;

import org.hibernate.cfg.Configuration;  
import org.hibernate.mapping.Column;  
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.Property;
import org.springframework.beans.BeansException;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.ApplicationContextAware;  
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;  

/** 
 * 继承ApplicationContextAware的方式来获得ApplicationContext, 
 * 需要在Spring配置文件中配置该类, 才能自动注入ApplicationContext对象
 */  
@Component
public class HibernateContext implements ApplicationContextAware {
  
    private static ApplicationContext applicationContext;  
  
    private static Configuration configuration;  

    @Override  
    public void setApplicationContext(ApplicationContext context) throws BeansException {  
        applicationContext = context;  
    }  

    /**
     * 获取applicationContext对象
     * @return
     */    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public static Configuration getConfiguration() {    
        if (configuration == null) {   
            // 取sessionFactory的时候要加上&  
            LocalSessionFactoryBean factory = (LocalSessionFactoryBean) applicationContext.getBean("&sessionFactory");  
            configuration = factory.getConfiguration();  
        }    
        return configuration;  
    }  
  
    /**
     * 根据bean名来查找对象
     * @param name
     * @return
     */    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }
    
    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param type
     * @return
     */
    public static <T> Map<String, T> getBeansByClass(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }
    
    private static <T> PersistentClass getPersistentClass(Class<T> clazz) {  
        synchronized (HibernateContext.class) {
            PersistentClass theClass = getConfiguration().getClassMapping(clazz.getSimpleName());  
            if (theClass == null) {  
                configuration = configuration.addClass(clazz);  
                theClass = configuration.getClassMapping(clazz.getName());  
            }  
            return theClass;  
        }
    }  
  
    /** 
     * 获得实体类对应的表名 
     * @param clazz 实体类的Class对象 
     * @return 表名 
     */  
    public static <T> String getTableName(Class<T> clazz) {  
        return getPersistentClass(clazz).getTable().getName();  
    }  
  
    /** 
     * 获得实体类对应表的主键字段名称 
     * @param clazz 实体类的Class对象 
     * @return 主键字段名称 
     */
    public static <T> String getPrimaryKeyName(Class<T> clazz) {
        PrimaryKey key = getPersistentClass(clazz).getTable().getPrimaryKey();
        return getPersistentClass(clazz).getTable().getPrimaryKey()  
                .getColumn(0).getName();  
    }  
  
    /** 
     * 获得类属性对应的字段名 
     * @param clazz 实体类的Class对象 
     * @param propertyName 实体类的属性名 
     * @return 属性对应的字段名 
     */  
    public static <T> String getColumnName(Class<T> clazz, String propertyName) {  
        String columnName = "";  
        PersistentClass persistentClass = getPersistentClass(clazz);  
        Property property = persistentClass.getProperty(propertyName);  
        Iterator<?> iterator = property.getColumnIterator();  
        if (iterator.hasNext()) {  
            Column column = (Column) iterator.next();  
            columnName += column.getName();  
        }  
        return columnName;  
    }  
}