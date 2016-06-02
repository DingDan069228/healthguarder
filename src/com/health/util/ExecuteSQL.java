package com.health.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
//session.createSQLQuery():for the given SQL query string
//session.createQuery():for the given HQL query string
public class ExecuteSQL {
	@SuppressWarnings("unchecked")
	//在Hibernate中有时不需要用到表的映射，需要直接执行sql语句,表名为mysql中实际存储的表，而不是映射的类
	public static int executeSQL(HibernateTemplate hibernateTemplate,String sql){
		final String tempsql = sql;
		Object affectedRows = hibernateTemplate.execute(new HibernateCallback(){
			//这里的session是hibernate中的
			public Object doInHibernate(Session session)
					throws HibernateException,SQLException{
				// TODO Auto-generated method stub
				//返回值为更新或删除的行数
				int number = session.createSQLQuery(tempsql).executeUpdate();
				return number;
			}
		});
		return (Integer)affectedRows;
	}
	public static List executeHQL(HibernateTemplate hibernateTemplate,String hql,final int number){
		final String tempHql = hql;
		List returnList = hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(tempHql);
				query.setFirstResult(0);
				query.setMaxResults(number);
				List list = query.list();
				return list;
			}
		});
		return returnList;
	}
}
