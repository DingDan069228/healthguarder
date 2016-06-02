package com.health.test;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.health.entity.User;
import com.health.service.impl.UserServiceImpl;

public class HibernateTest2 {
	public static void main(String... args){
		/*BeanFactory bf = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		UserServiceImpl userService = (UserServiceImpl) bf.getBean("user_service");
		User user = userService.createUser("dingdang", "123456", "dingdang", "123456", "女", null);
		System.out.println(user);*/
		UserServiceImpl userService = new UserServiceImpl();
		User user = userService.createUser("DINGDAN", "123456", "DINGDANG", "123456", "NV", null);
		System.out.println(user);
	}
}
