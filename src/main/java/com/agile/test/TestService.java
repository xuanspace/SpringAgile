package com.agile.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agile.model.User;
import com.agile.service.interfaces.UserService;

public class TestService {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-mvc.xml", "classpath:spring-database.xml","classpath:spring-hibernate.xml");
		
		UserService userService = (UserService) ctx.getBean("userService");
		User user = userService.get(1);
		

	}

}
