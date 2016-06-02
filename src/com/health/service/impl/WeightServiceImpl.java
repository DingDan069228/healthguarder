package com.health.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IWeightDao;
import com.health.entity.Weight;
import com.health.service.IWeightService;
@Component("weight_service")
public class WeightServiceImpl implements IWeightService {
	private IWeightDao weightDao;
	@Resource(name="weight_dao")
	public void setWeightDao(IWeightDao weightDao){
		this.weightDao = weightDao;
	}
	public Integer save(Weight instance) {
		// TODO Auto-generated method stub
		return weightDao.save(instance);
	}

}
