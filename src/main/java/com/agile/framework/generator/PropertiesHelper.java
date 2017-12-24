package com.agile.framework.generator;

import java.util.Map.Entry;
import java.util.Properties;  

public class PropertiesHelper {  
	  
    private static final Properties properties = new Properties();
    
    static {  
        try {              
            properties.load(PropertiesHelper.class.getClassLoader().getResourceAsStream("hibernate.properties"));  
            for (Entry<Object, Object> entry : properties.entrySet()) {
            	String key = entry.getKey().toString().trim();
            	String value = entry.getValue().toString().trim();
                properties.put(key, value);
                System.out.println(key + ":" + value);
            }
        } 
        catch (Exception e) {
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * 通过key值去获取属性值. 
     */  
    public static String getValueByKey(String name) {  
        return properties.getProperty(name);  
    }  
  
}  