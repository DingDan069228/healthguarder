package com.health.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IBloodPressureDao;
import com.health.service.IBloodPressureService;
@Component("bloodPressure_service")
public class BloodPressureServiceImpl implements IBloodPressureService {
	private IBloodPressureDao bloodPressureDao;
	@Resource(name="bloodPressure_dao")
	public void setBloodPressureDao(IBloodPressureDao bloodPressureDao){
		this.bloodPressureDao = bloodPressureDao;
	}
	//第一个参数为用户Id，第二个参数为返回的数量
	public List<Integer> getRecentValues(int userId, int number) {
		// TODO Auto-generated method stub
		return bloodPressureDao.findByProperty(userId, number);
	}

}
