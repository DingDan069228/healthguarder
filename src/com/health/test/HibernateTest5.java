package com.health.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.health.util.ExecuteSQL;

public class HibernateTest5 {
	public static void main(String[] args){
		 BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		 HibernateTemplate hibernateTemplate = (HibernateTemplate) bf.getBean("hibernateTemplate");
		 String sql = "update user set realName='Rose' where userName='admin'";
		 int result = ExecuteSQL.executeSQL(hibernateTemplate, sql);
		 System.out.println(result);
	}
}
