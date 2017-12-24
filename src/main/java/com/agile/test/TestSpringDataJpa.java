package com.agile.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.agile.model.User;
import com.agile.service.jpa.UserJpaService;


/** 声明spring主配置文件位置，注意：以当前测试类的位置为基准,有多个配置文件以字符数组声明 **/
//@ContextConfiguration(locations={"spring-jpa.xml"})

/** 声明使用事务，不声明spring会使用默认事务管理 **/
@Transactional

/** 声明事务回滚，要不测试一个方法数据就没有了岂不很杯具，注意：插入数据时可注掉，不让事务回滚 **/
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)

public class TestSpringDataJpa {

	@Resource
	private UserJpaService userService;
	
	// 新增（来个20条数据） 注意新增的时候先把事务注掉，要不会回滚操作
	public void testSaveUser() {
		for(int i=0; i<20; i++){
			User user = new User();
			user.setName("system");
			user.setPassword(i+"system");
			userService.saveUser(user);
		}
	}
	
	// 删除  有事务回滚，并不会真的删除
	public void testDeleteUser() {
		userService.deleteUser(27L);
	}
	
	// 查询所有
	public void testFindAllUser() {
		List<User> users = userService.findAllUsers();
		System.out.println(users.size());
	}

	// 查询分页对象
	public void testFindAllUserByPage() {
		/**
		 *  创建一个分页对象   （注意：0代表的是第一页，5代表每页的大小,后两个参数不写即为默认排序）
		 *  Direction：为一个枚举类，定义了DESC和ASC排序顺序
		 *  id：结果集根据id来进行DESC降序排序
		 *  想自己实现的话，最好继承他这个类，来定义一些个性的方法
		 */
		PageRequest request = new PageRequest(1, 4, Direction.DESC, "id");
		
		Page<User> users = userService.findAllUserByPage(request);
		// 打印分页详情
		System.out.println("查询结果：共"+users.getTotalElements()+"条数据，每页显示"+users.getSize()+"条，共"+users.getTotalPages()+"页，当前第"+(users.getNumber()+1)+"页！");
		// 打印结果集的内容
		System.out.println(users.getContent());
	}
	
	// main 用于查看spring所有bean,以此可以检测spring容器是否正确初始化
	public static void main(String[] args) {
		// 我这里使用的是绝对路径，请根据你项目的路径来配置（相对路径挖不出来-OUT了）
		String []  path = {"E:/moviework/springJpa/src/spring-config/spring-jpa.xml"};
		ApplicationContext ac = new FileSystemXmlApplicationContext(path);
 		String[] beans = ac.getBeanDefinitionNames();
 		for(String s : beans) {
 			System.out.println(s);	// 打印bean的name
 		}
	}

}
