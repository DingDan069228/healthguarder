package com.health.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.health.dao.IClinicReportDao;
import com.health.entity.ClinicReport;
import com.health.service.IClinicReportService;
@Component("clinicReport_service")
public class ClinicReportServiceImpl implements IClinicReportService {
	private IClinicReportDao clinicReportDao;
	@Resource(name="clinicReport_dao")
	public void setClinicReportDao(IClinicReportDao clinicReportDao){
		this.clinicReportDao = clinicReportDao;
	}
	public Integer save(ClinicReport instance) {
		// TODO Auto-generated method stub
		return clinicReportDao.save(instance);
	}

}
