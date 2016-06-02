package com.health.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IUserDao;
import com.health.entity.User;
import com.health.util.ExecuteSQL;
@Transactional
@Component("user_dao")
public class UserDaoImpl extends SuperDao implements IUserDao {
	private Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	public String USER_NAME = "userName";
	public String GENDER = "gender";
	public String REAL_NAME="realName";
	public void attachDirty(User instance) {
		// TODO Auto-generated method stub
		log.debug("attaching dirty user instance...");
		try{
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attached successfully");
		}
		catch(Exception e){
			log.error("attached failed!");
			e.printStackTrace();
		}
	}

	public List findAll() {
		// TODO Auto-generated method stub
		log.debug("finding all user instances...");
		try{
		String queryString = "from User";
		List<User> lst = (List)getHibernateTemplate().find(queryString);
		log.debug("find successfully");
		return lst;
		}
		catch(Exception e){
			log.error("finding all user instances failed!");
			e.printStackTrace();
			return null;
		}
	}

	public User findById(Integer id) {
		// TODO Auto-generated method stub
		try{
			log.debug("finding user instance by id:"+id);
			User user = (User) getHibernateTemplate().get("com.health.entity.User", id);
			log.debug("find successfully");
			return user;
		}
		catch(Exception e){
			log.error("find failed!");
			e.printStackTrace();
			return null;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		log.debug("finding User by property:"+propertyName+" value:"+value);
		try{
			String queryString = "from User as model where model."+propertyName+"=?";
			List lst = (List)getHibernateTemplate().find(queryString, value);
			log.debug("find User by property:"+propertyName+" value:"+value+" successfully");
			return lst;
		}
		catch(Exception e){
			log.error("find failed!");
			e.printStackTrace();
			return null;
		}
	}

	public List<User> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return findByProperty(USER_NAME, userName);
	}
	public List<User> findByRealName(String realName){
		return findByProperty(REAL_NAME, realName);
	}

	public void save(User transientInstance) {
		log.debug("saving user instance...");
		//System.out.println("saving user instance...");
		getHibernateTemplate().save(transientInstance);
		//System.out.println("save successfully");
		log.debug("save successfully");
	}

	public User updateByUserName(User instance,String originalUserName) {
		// TODO Auto-generated method stub
		log.debug("update user by userName:"+originalUserName);
		String phoneNumber = instance.getPhoneNumber();
		String phoneNumber2 = phoneNumber==null?null:"'"+phoneNumber+"'";
		String email = instance.getEmail();
		String email2 = email==null?null:"'"+email+"'";
		String realName = instance.getRealName();
		String realName2 = realName==null?null:"'"+realName+"'";
		String birthday = instance.getBirthday();
		String birthday2 = birthday==null?null:"'"+birthday+"'";
		String sql = "UPDATE user SET userName="+"'"+instance.getUserName()+"'"+",realName="+realName2+",phoneNumber="+phoneNumber2+
					",email="+email2+",gender="+"'"+instance.getGender()+"'"+",height="+instance.getHeight()+
					",weight="+instance.getWeight()+",birthday="+birthday2+" where userName="+"'"+originalUserName+"'"+";";
		Integer number = ExecuteSQL.executeSQL(getHibernateTemplate(), sql);
		if(number==1){
			User user = findByUserName(originalUserName).get(0);
			return user;
		}
		else
			return null;
		
	}
}
