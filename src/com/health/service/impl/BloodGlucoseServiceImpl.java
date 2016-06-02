package com.health.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IBloodGlucoseDao;
import com.health.entity.BloodGlucose;
import com.health.service.IBloodGlucoseService;
@Component("bloodGlucose_service")
public class BloodGlucoseServiceImpl implements IBloodGlucoseService {
	private IBloodGlucoseDao bloodGlucoseDao;
	@Resource(name="bloodGlucose_dao")
	public void setBloodGlucoseDao(IBloodGlucoseDao bloodGlucoseDao){
		this.bloodGlucoseDao = bloodGlucoseDao;
	}
	public Integer save(BloodGlucose instance) {
		// TODO Auto-generated method stub
		return bloodGlucoseDao.save(instance);
	}

}
