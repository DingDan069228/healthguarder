package com.health.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.health.dao.impl.UserDaoImpl;
import com.health.entity.User;
/*
 * sessionFactory.getCurrentSession()方法并不总是能返回非null值，此时要用sessionFactory的openSession()方法。
 */
public class HibernateTest4 {
	public static void main(String[] args){
		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) bf.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update user set phoneNumber='123' where id=1;");
		int result = query.executeUpdate();
		System.out.println(result);
		HibernateTemplate hibernateTemplate = (HibernateTemplate) bf.getBean("hibernateTemplate");
		User user = new User("admin", null, null, "女","2015-12-20", "123456", null, 0, 0, null);
		JSONObject obj4 = JSONObject.fromObject(user);
		System.out.println("直接从对象获取的JSONObject："+obj4);
		System.out.println("email:"+user.getEmail());
		String sql = "UPDATE user SET userName="+"'"+user.getUserName()+"'"+",realName="+"'"+user.getRealName()+"'"+",phoneNumber="+"'"+user.getPhoneNumber()+"'"+
		",email="+"'"+user.getEmail()+"'"+",gender="+"'"+user.getGender()+"'"+",height="+user.getHeight()+
		",weight="+user.getHeight()+",birthday="+"'"+user.getBirthday()+"'"+" where userName="+"'admin'"+";";
		System.out.println("sql:"+sql);
		UserDaoImpl userDao = (UserDaoImpl) bf.getBean("user_dao");
		userDao.updateByUserName(user, "admin");
		User user2 = userDao.findById(1);
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("userName", user2.getUserName());
		obj.put("realName",user2.getRealName());
		obj.put("email", user2.getEmail());
		obj.put("image", user2.getImage());
		obj.put("birthday", user2.getBirthday());
		obj.put("phoneMumber", user2.getPhoneNumber());
		array.add(obj);
		System.out.println(array.toString());
		//System.out.println("'"+user2.getUserName()+"'");
		//String ok = null;
		//System.out.println(ok==null);
		
	}
}
