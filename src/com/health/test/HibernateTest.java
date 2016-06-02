package com.health.test;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
//先找出最后一条记录的id值，方便插入。
public class HibernateTest {
	public static void main(String... args){
		BeanFactory bf = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		HibernateTemplate hibernateTemplate = (HibernateTemplate) bf.getBean("hibernateTemplate");
		String queryString = "select max(t.id) from User as t";
		List<Integer> lst = hibernateTemplate.find(queryString);
		System.out.println(lst.get(0));
		int a = 100000;
		//Integer.toBinaryString(a);
		//System.out.println((a-(a>>8)<<8));
	}
}
