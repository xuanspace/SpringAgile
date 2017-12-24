package com.agile.test;

import com.agile.dao.interfaces.UserDao;
import com.agile.model.Role;
import com.agile.framework.query.Builder;
import com.agile.modules.database.SYS_USER;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agile.model.User;
import com.agile.service.interfaces.UserService;

import java.util.List;

public class TestLoadHibernateXmlConfigFile {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
	    //ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-hibernate.xml");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-hibernate.xml");

		UserDao dao = (UserDao) ctx.getBean("userDao2");
		Builder builder = dao.queryBuilder();
		List list = builder.where(SYS_USER.ID.eq(10)).list();
		//builder.sql("").list();
		list = builder.hql("from Role").list();


		long count = dao.getCount();
		User u1 = dao.get(10);
		boolean have = dao.exists(20);
		dao.delete(20);
		List<User> users = dao.getList();
		Role role = dao.get(Role.class, 1);

	    User user = new User();
	    user.setId(20);
	    user.setName("test");
	    user.setPassword("testme");
	    dao.save(user);
	    System.out.println("User inserted!");

		dao.delete(20);

		UserService us = (UserService) ctx.getBean("userService");
		User u = us.get(10);
		u.setName("ssss");
		us.save(u);

		//UserController uc = (UserController)ctx.getBean("userController");
		//uc.login("teset");
		
	}

}
