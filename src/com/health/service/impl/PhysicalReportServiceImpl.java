package com.health.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IPhysicalReportDao;
import com.health.entity.PhysicalReport;
import com.health.service.IPhysicalReportService;
@Component("physicalReport_service")
public class PhysicalReportServiceImpl implements IPhysicalReportService {
	private IPhysicalReportDao physicalReportDao;
	@Resource(name="physicalReport_dao")
	public void setPhysicalReportDao(IPhysicalReportDao physicalReportDao){
		this.physicalReportDao = physicalReportDao;
	}
	public Integer save(PhysicalReport instance) {
		// TODO Auto-generated method stub
		return physicalReportDao.save(instance);
	}

}
