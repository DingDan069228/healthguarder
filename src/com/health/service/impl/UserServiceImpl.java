package com.health.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sourceforge.jtds.jdbc.Support;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.health.dao.IUserDao;
import com.health.dao.impl.SuperDao;
import com.health.entity.User;
import com.health.service.IUserService;
import com.health.util.MD5Code;
@Component("user_service")
public class UserServiceImpl extends SuperDao implements IUserService {
	private IUserDao userDao;
	@Resource(name="user_dao")
	public void setUserDao(IUserDao userDao){
		this.userDao=userDao;
	}
	public Long countUser(String userNameTop) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}

	public User loadOneUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	public User loadOneUser(String userName){
		List<User> lst = userDao.findByUserName(userName);
		return lst.get(0);
	}
	public List<User> loadUserList(Integer start, Integer limit,
			String userNameTop) {
		// TODO Auto-generated method stub
		return null;
	}

	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		List<User> lst = userDao.findByUserName(userName);
		if(lst==null||lst.size()!=1){
			return null;
		}
		User user = lst.get(0);
		MD5Code md5 = new MD5Code();
		if(!user.getPassword().equals(md5.getMD5ofStr(password))){
			return null;
		}
		return user;
	}
	public User createUser(String userName, String password,
			String realName, String phoneNumber, String gender, String birthday) {
		// TODO Auto-generated method stub
		//BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		//WEB项目要发布到tomcat目录下才能运行，所以这里的路径必须要写tomcat下的xml文件路径,默认的绝对路径是eclipse下的绝对路径
		//BeanFactory bf = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext.xml");
		//HibernateTemplate hibernateTemplate = (HibernateTemplate) bf.getBean("hibernateTemplate");
		//String queryString = "select max(t.id) from User as t";
		//String queryString = "select max(t.id) from User as t";
		//List<Integer> lst = hibernateTemplate.find(queryString);
		//int max_id = lst.get(0);
		//System.out.println(max_id);
		User newUser = new User(userName, password, realName, gender, birthday, phoneNumber);
		userDao.save(newUser);
		List<User> lst = userDao.findByUserName(userName);
		return lst.get(0);
	}
	public Integer getIdByUserName(String userName) {
		// TODO Auto-generated method stub
		try{
			String queryString = "select model.id from User as model where model.userName=?";
			List lst = getHibernateTemplate().find(queryString, userName);
			System.out.println(lst==null?"null":lst);
			if(lst.size()==0){
				return null;
			}
			else{
				return (Integer) lst.get(0);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	

}
