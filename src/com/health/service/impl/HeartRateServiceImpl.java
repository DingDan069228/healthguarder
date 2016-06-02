package com.health.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IHeartRateDao;
import com.health.service.IHeartRateService;
@Component("heartRate_service")
public class HeartRateServiceImpl implements IHeartRateService {
	private IHeartRateDao heartRateDao;
	@Resource(name="heartRate_dao")
	public void setHeartRateDao(IHeartRateDao heartRateDao){
		this.heartRateDao = heartRateDao;
	}
	public List<Integer> getRecentValues(int userId, int number) {
		// TODO Auto-generated method stub
		return heartRateDao.findByProperty(userId, number);
	}

}
