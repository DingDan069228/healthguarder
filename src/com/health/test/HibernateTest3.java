package com.health.test;

import java.sql.Timestamp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.health.dao.impl.UserDaoImpl;
import com.health.entity.BloodGlucose;
import com.health.entity.User;
import com.health.entity.Weight;
import com.health.service.impl.BloodGlucoseServiceImpl;
import com.health.service.impl.UserServiceImpl;
import com.health.service.impl.WeightServiceImpl;

public class HibernateTest3 {
	public static void main(String... args){
		/*BeanFactory bf = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		UserDaoImpl userDao = (UserDaoImpl) bf.getBean("user_dao");
		//在hibernate中，当设置了主键生成策略时，向数据库插入的记录是不包含主键值的，如果主键没有默认值，则会报错UncategorizedSQLException
		User user = new User(2, "DINF", "123456", "DING", "女", null, null);
		userDao.save(user);*/
		//System.out.println(user);
		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		//UserServiceImpl userService = (UserServiceImpl) bf.getBean("user_service");
		//User user = new User("dingdan", "123456", "dingdan", "女", null, null);
		//UserDaoImpl userDao = (UserDaoImpl) bf.getBean("user_dao");
		//userDao.save(user);
		//User newUser = userService.createUser("admin", "123456", "dingdan", "女", null, null);
		//User user = userService.loadOneUser("admin");
		//System.out.println(user);
		//WeightServiceImpl service = bf.getBean(WeightServiceImpl.class);
		//Weight weight = new Weight(50, Timestamp.valueOf("2015-12-18 06:00:00"),null, 1);
		//BloodGlucoseServiceImpl service = bf.getBean(BloodGlucoseServiceImpl.class);
		//BloodGlucose bloodGlucose = new BloodGlucose(1, Float.valueOf("17.55"), Timestamp.valueOf("2015-12-18 08:30:00"), null, 1);
		//service.save(bloodGlucose);
		//System.out.println(service.save(bloodGlucose));
		UserDaoImpl userDao = (UserDaoImpl) bf.getBean("user_dao");
		User user = new User("DINF4", "123456", "DING", "女", null, null);
		User user2 = userDao.findById(1);
		System.out.println(user);
		userDao.save(user);
	}
}
