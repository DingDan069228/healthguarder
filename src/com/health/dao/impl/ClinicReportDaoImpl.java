package com.health.dao.impl;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IClinicReportDao;
import com.health.entity.ClinicReport;
@Transactional
@Component("clinicReport_dao")
public class ClinicReportDaoImpl extends SuperDao implements IClinicReportDao{
	private Logger log = LoggerFactory.getLogger(BloodPressureDaoImpl.class);
	public Integer save(ClinicReport instance) {
		log.debug("saving ClinicReport instance:"+instance.toString());
		//返回的值为记录的id
		Serializable id = getHibernateTemplate().save(instance);
		//System.out.print((Integer)id);
		log.debug("saved succeeded");
		return (Integer)id;
	}

}
