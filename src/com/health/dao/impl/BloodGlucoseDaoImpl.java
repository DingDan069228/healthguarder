package com.health.dao.impl;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IBloodGlucoseDao;
import com.health.entity.BloodGlucose;
import com.health.entity.ClinicReport;
@Transactional
@Component("bloodGlucose_dao")
public class BloodGlucoseDaoImpl extends SuperDao implements IBloodGlucoseDao {

	private Logger log = LoggerFactory.getLogger(BloodPressureDaoImpl.class);
	public Integer save(BloodGlucose instance) {
		log.debug("saving BloodGlucose instance:"+instance.toString());
		Serializable id = getHibernateTemplate().save(instance);
		//System.out.println((Integer)id);
		log.debug("saved succeeded");
		return (Integer)id;
	}

}
