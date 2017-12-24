package com.agile.framework.generator;

import java.sql.Connection;
import java.sql.DriverManager;



public class JdbcHelper {
	
    private static final String driverClass = PropertiesHelper.getValueByKey("driverClassName");  
    private static final String connectionUrl = PropertiesHelper.getValueByKey("jdbc_url");  
    private static final String username = PropertiesHelper.getValueByKey("jdbc_username");  
    private static final String password = PropertiesHelper.getValueByKey("jdbc_password");
      
    private static JdbcHelper instance = null;
    
    public static JdbcHelper getInstance() {
        if (instance == null) {  
            synchronized (JdbcHelper.class) {
                instance = new JdbcHelper();
            }  
        }  
        return instance;  
    }  
      
    public static Connection getConnection(){  
        try {  
            Class.forName(driverClass);  
            return DriverManager.getConnection(connectionUrl, username, password);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }    
        return null;  
    }

    public static String getDatabaseName() {
		int i1 = connectionUrl.indexOf("://") + 3;
		int i2 = connectionUrl.indexOf(":", i1);
		int i3 = connectionUrl.indexOf("/", i2);
		
		//host = connectionUrl.substring(i1, i2);
		//port = toInt(connectionUrl.substring(i2 + 1, i3));
		return connectionUrl.substring(i3 + 1);
	}    
}  