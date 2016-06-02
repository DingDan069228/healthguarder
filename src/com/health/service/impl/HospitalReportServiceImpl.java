package com.health.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IHospitalReportDao;
import com.health.entity.HospitalReport;
import com.health.service.IHospitalReportService;
@Component("hospitalReport_service")
public class HospitalReportServiceImpl implements IHospitalReportService {
	private IHospitalReportDao hospitalReportDao;
	@Resource(name="hospitalReport_dao")
	public void setHospitalReportDao(IHospitalReportDao hospitalReportDao){
		this.hospitalReportDao = hospitalReportDao;
	}
	public Integer save(HospitalReport instance) {
		// TODO Auto-generated method stub
		return hospitalReportDao.save(instance);
	}

}
