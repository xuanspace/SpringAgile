package com.agile.test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.agile.config.AppConfig;
import com.agile.dao.interfaces.UserDao;
import com.agile.model.User;
import com.agile.service.interfaces.UserService;

//
// 通过AppConfig来自动加application.xml, application.xml这个配置将mvc和hibernate配置放在一起
//
public class TestAppMain {

	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Object name = context.getBean("message");
		
		UserService service = (UserService) context.getBean("userService");
		User u = service.get(16);
		UserDao dao = context.getBean("userDao", UserDao.class);

		/*
		 * Create UserEntity1
		 */
//		User employee1 = new User();
//		employee1.setUsername("Han Yenn");
//		employee1.setPassword("1234");


		/*
		 * Persist both UserEntitys
		 */
		//
		//service.isExist(employee1);


		context.close();
	}
}