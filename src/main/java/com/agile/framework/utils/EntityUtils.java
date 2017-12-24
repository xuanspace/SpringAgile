package com.agile.framework.utils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

@SuppressWarnings("unchecked")
public class EntityUtils {

	/**
     * Map转对象 
     * @param map 需要转换的Map
     * @param clazz 需要转换成的类
     * @return  转换成的对象
     * @throws Exception
     */	
	@SuppressWarnings("rawtypes")
	public static <T> T toBean(Map map, Class<T> clazz) {
		try {
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Class<?> getPropertyType(String name, Class<?> clazz) {
		return org.springframework.beans.BeanUtils.findPropertyType(name, clazz);
	}
	
	/**
     * 对象转 Map
     * @param object 需要转换的对象
     * @return  转换成map
     * @throws Exception
     */
    public static Map<String, Object> toMap(Object object) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>(); 
        Class<?> clazz = object.getClass();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            ((AccessibleObject) field).setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

	/**
	 * 获取@Table注解的表名
	 * @return  不存在@Table注解则返回null
	 */
	public static String getTableName(Class<?> clazz) {
		for (Annotation annotation : clazz.getAnnotations()) {
			Class<? extends Annotation> type = annotation.annotationType();
			if( annotation instanceof javax.persistence.Table ) {
				for (Method method : type.getDeclaredMethods()) {
					try {
						if (method.getName().equals("name")) {
							Object value = method.invoke(annotation, (Object[])null);
							return value.toString();
						}
					}catch (Exception e) {
						continue;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取@Id注解的主键名
	 * @return  不存在@Id注解则返回null
     */
	public static Class<?> getIdClass(Class<?> clazz) {
		Field fields[] = clazz.getDeclaredFields();
		for( Field field : fields ) {
			Annotation annotations[] = field.getDeclaredAnnotations();
			for( Annotation annotation : annotations ) {
				if( annotation instanceof javax.persistence.Id )
					return field.getType();
			}
			//only getter methods can have persistence annotations, setters cannot
			try {
				Method getter = new PropertyDescriptor(field.getName(), clazz).getReadMethod();
				Annotation getterAnnotations[] = getter.getDeclaredAnnotations();
				for( Annotation annotation : getterAnnotations ) {
					if( annotation instanceof javax.persistence.Id )
						return field.getType();
				}
			}catch (Exception e) {
				continue;
			}
		}
		return null;
	}
	
	/**
	 * 获取@Id注解的主键名
	 * @return  不存在@Id注解则返回null
     */
	public static String getIdName(Class<?> clazz) {
		Field fields[] = clazz.getDeclaredFields();
		for( Field field : fields ) {
			Annotation annotations[] = field.getDeclaredAnnotations();
			for( Annotation annotation : annotations ) {
				if( annotation instanceof javax.persistence.Id )
					return field.getName();
			}
			//only getter methods can have persistence annotations, setters cannot
			try {
				Method getter = new PropertyDescriptor(field.getName(), clazz).getReadMethod();
				Annotation getterAnnotations[] = getter.getDeclaredAnnotations();
				for( Annotation annotation : getterAnnotations ) {
					if( annotation instanceof javax.persistence.Id )
						return field.getName();
				}
			}catch (Exception e) {
				continue;
			}
		}
		return null;
	}
}