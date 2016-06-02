package com.health.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IHospitalReportDao;
import com.health.entity.ClinicReport;
import com.health.entity.HospitalReport;
@Transactional
@Component("hospitalReport_dao")
public class HospitalReportDaoImpl extends SuperDao implements IHospitalReportDao {

	private Logger log = LoggerFactory.getLogger(BloodPressureDaoImpl.class);
	public Integer save(HospitalReport instance) {
		log.debug("saving HospitalReport instance:"+instance.toString());
		try{
			Serializable id = getHibernateTemplate().save(instance);
			log.debug("saved succeeded");
			//System.out.println((Integer)id);
			return (Integer)id;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
