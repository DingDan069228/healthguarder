package com.health.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IPhysicalReportDao;
import com.health.entity.ClinicReport;
import com.health.entity.PhysicalReport;
@Transactional
@Component("physicalReport_dao")
public class PhysicalReportDaoImpl extends SuperDao implements IPhysicalReportDao {
	private Logger log = LoggerFactory.getLogger(BloodPressureDaoImpl.class);
	public Integer save(PhysicalReport instance) {
		log.debug("saving PhysicalReport instance:"+instance.toString());
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
