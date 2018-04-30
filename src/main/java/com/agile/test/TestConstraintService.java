package com.agile.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agile.model.Constraint;
import com.agile.service.interfaces.ConstraintService;

public class TestConstraintService {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-database.xml", "spring-hibernate.xml");
		
		ConstraintService service = (ConstraintService) ctx.getBean("constraintService");
		Constraint obj = service.get(1);

	}

}
