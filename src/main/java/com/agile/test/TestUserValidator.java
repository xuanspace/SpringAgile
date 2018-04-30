package com.agile.test;

import java.net.BindException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agile.model.Constraint;
import com.agile.model.User;
import com.agile.service.interfaces.ConstraintService;
import com.agile.validator.UserValidator;

public class TestUserValidator {
	
	public static void main(String[] args) throws BindException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-database.xml", "spring-hibernate.xml");
		
		UserValidator validator = (UserValidator) ctx.getBean("userValidator");
		User user = new User();
		try {
			validator.validate(user);
		} catch (org.springframework.validation.BindException e) {
			e.printStackTrace();
		}

	}	

}
