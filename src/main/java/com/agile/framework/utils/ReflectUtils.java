package com.agile.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectUtils {

	private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

	private ReflectUtils() {
	}

	public static List<String> getFieldNames(Class<?> clazz) {
		List<String> names = new ArrayList<String>(); 
        Field[] fields = clazz.getDeclaredFields();  
        for (Field field : fields) {
        	names.add(field.getName());
        }
        return names;
    }  
	  
	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 * @param object 对象
	 * @param fieldName 对象属性名
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getFieldByName(object, fieldName);
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");		
		makeAccessible(field);
		Object value = null;
		try {
			value = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("Get the field of object error", e.getMessage());
		}
		return value;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 * @param object 对象
	 * @param fieldName 对象属性名
	 * @param value 属性值
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getFieldByName(object, fieldName);
		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

		makeAccessible(field);
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("Set the field value of object error", e.getMessage());			
		}
	}

    /**
     * 根据字段名获取字段
     * @param object 对象
     * @param fieldName 属性名
     * @return 字段
     */
	protected static Field getFieldByName(final Object object, final String fieldName) {
		return getFieldByName(object.getClass(), fieldName);
	}

    /**
     * 根据字段名获取字段
     * @param clazz 对象类
     * @param fieldName 对象属性名
     * @return 字段
     */
	protected static Field getFieldByName(final Class<?> clazz, final String fieldName) {
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,查看父类
			}
		}
		return null;
	}
	
	/**
	 * 强制转换fileld可访问.
     * @param field 对象字段 
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 
	 * 获得定义Class时声明的父类的泛型参数的类型.
	 * @param clazz  The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be determined
	 * 
	 */
	public static Class<?> getSuperClassGenricType(final Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 
	 * 获得定义Class时声明的父类的泛型参数的类型.
	 * 
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 * 
	 */
	public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}

		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	 /** 
     * 获取对象的 DeclaredMethod 
     * @param object 
     * @param methodName 
     * @param parameterTypes 
     * @return 
     */  
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes){  
          
        for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){  
            try {  
                //superClass.getMethod(methodName, parameterTypes);  
                return superClass.getDeclaredMethod(methodName, parameterTypes);  
            } catch (NoSuchMethodException e) {  
                //Method 不在当前类定义, 继续向上转型  
            }  
            //..  
        }  
        return null;  
    }  
    
    /** 
     * 直接调用对象方法, 而忽略修饰符(private, protected) 
     * @param object 
     * @param methodName 
     * @param parameterTypes 
     * @param parameters 
     * @return 
     * @throws InvocationTargetException  
     * @throws IllegalArgumentException  
     */  
    public static Object invokeMethod(Object object, String methodName, Class<?> [] parameterTypes,  
            Object [] parameters) throws InvocationTargetException{  
          
        Method method = getDeclaredMethod(object, methodName, parameterTypes);  
          
        if(method == null){  
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");  
        }  
          
        method.setAccessible(true);  
          
        try {  
            return method.invoke(object, parameters);  
        } catch(IllegalAccessException e) {  
  
        }   
          
        return null;  
    }  	
}
