package com.agile.framework.application;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

/**
 *  Spring系统启动以后加载该类进行数据初始化
 *
 *  配置文件添该bean,设置其引用的服务
 *  <bean class="com.agile.framework.application.DataInitializer">
 *      <property name="xxxService" ref="xxxService"/>  
 *  </bean>  
*/
public class DataInitializer implements InitializingBean, ServletContextAware {

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		try {

		} catch (Exception e) {

		}
	}
}
