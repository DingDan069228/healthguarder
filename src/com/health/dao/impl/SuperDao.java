package com.health.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
@Component("Super_Dao")
public class SuperDao extends HibernateDaoSupport{
	//注入Spring的Bean文件，该hibernateTemplate要在Spring配置文件中配置
	//setHibernateTemplate()方法是final方法，不能被覆盖
	@Resource(name="hibernateTemplate")
	public void setSuperHibernateTemplate(HibernateTemplate hibernateTemplate){
		super.setHibernateTemplate(hibernateTemplate);
	}
}
