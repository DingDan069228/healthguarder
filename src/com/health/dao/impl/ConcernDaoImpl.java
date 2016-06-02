package com.health.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IConcernDao;
import com.health.entity.Concern;
@Transactional
@Component("concern_dao")
public class ConcernDaoImpl extends SuperDao implements IConcernDao {

	public Integer save(Concern concern) {
		// TODO Auto-generated method stub
		try{
			Serializable i = getHibernateTemplate().save(concern);
			if(i==null){
				return null;
			}else{
				return (Integer)i;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
