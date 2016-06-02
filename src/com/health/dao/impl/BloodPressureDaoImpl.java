package com.health.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IBloodPressureDao;
import com.health.entity.BloodPressure;
import com.health.util.ExecuteSQL;
@Transactional
@Component("bloodPressure_dao")
public class BloodPressureDaoImpl extends SuperDao implements IBloodPressureDao {
	private Logger log = LoggerFactory.getLogger(BloodPressureDaoImpl.class);
	public void save(BloodPressure transientInstance) {
		// TODO Auto-generated method stub
		log.debug("saving blood pressure instance...");
		getHibernateTemplate().save(transientInstance);
		log.debug("save succeeded!");
	}
	//根据用户id查找该用户的血压数据，按照时间降序排列，取前面的十条数据
	public List<Integer> findByProperty(int userId, int number) {
		// TODO Auto-generated method stub
		log.debug("finding instace by userId:"+userId+" limit "+number);
		try{
			String queryString = "select model.value from BloodPressure as model where model.userId="+userId+" order by model.time desc";
			List lst = ExecuteSQL.executeHQL(getHibernateTemplate(), queryString, number);
			log.debug("finding instace by userId:"+userId+" limit "+number+" succeeded");
			return lst;
		}
		catch(Exception e){
			log.error("finding instace by userId:"+userId+" limit"+number+" failed");
			e.printStackTrace();
			//当出现异常的时候返回值为null
			return null;
		}
	}

}
