package com.agile.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agile.model.User;
import com.agile.service.jpa.UserJpaService;

//
// 测试JPA的, 这里jpa是单纯的数据层，与web不相干
//    配置文件是spring-jpa.xml
//

public class TestSpringDataJpa2 {

	public TestSpringDataJpa2() {

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext(
	    		"spring-jpa.xml");

 		String[] beans = context.getBeanDefinitionNames();
 		for(String s : beans) {
 			System.out.println(s);	// 打印bean的name
 		}
 		
	    UserJpaService userDao = (UserJpaService) context.getBean("userJpaService");

	        List<User> list = userDao.findAll();
	        System.out.println("User count: " + list.size());

	        User user = new User();
	        user.setName("johndoe");
	        user.setPassword("test");
	        userDao.saveUser(user);
	        System.out.println("User inserted!");

	        list = userDao.findAll();
	        System.out.println("User count: " + list.size());
	}

}
