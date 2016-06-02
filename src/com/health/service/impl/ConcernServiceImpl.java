package com.health.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IConcernDao;
import com.health.entity.Concern;
import com.health.service.IConcernService;
@Component("concern_service")
public class ConcernServiceImpl implements IConcernService {
	private IConcernDao concernDao;
	@Resource(name="concern_dao")
	public void setConcernDao(IConcernDao concernDao){
		this.concernDao = concernDao;
	}
	public Integer createPair(Concern concern) {
		// TODO Auto-generated method stub
		return concernDao.save(concern);
	}
	
}
