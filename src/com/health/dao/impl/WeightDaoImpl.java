package com.health.dao.impl;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IWeightDao;
import com.health.entity.ClinicReport;
import com.health.entity.Weight;
@Transactional
@Component("weight_dao")
public class WeightDaoImpl extends SuperDao implements IWeightDao {

	private Logger log = LoggerFactory.getLogger(BloodPressureDaoImpl.class);
	public Integer save(Weight instance) {
		log.debug("saving Weight instance:"+instance.toString());
		try{
			Serializable id = getHibernateTemplate().save(instance);
			//System.out.println((Integer)id);
			log.debug("saved succeeded");
			return (Integer)id;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
