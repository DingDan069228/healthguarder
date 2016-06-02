package com.health.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.health.dao.IHeartRateDao;
import com.health.entity.HeartRate;
import com.health.util.ExecuteSQL;
@Transactional
@Component("heartRate_dao")
public class HeartRateDaoImpl extends SuperDao implements IHeartRateDao {
	private Logger log = LoggerFactory.getLogger(HeartRateDaoImpl.class);

	public void save(HeartRate transientInstance) {
		// TODO Auto-generated method stub
		log.debug("saving HeartRate instance");
		getHibernateTemplate().save(transientInstance);
	}

	public List<Integer> findByProperty(int userId, int number) {
		// TODO Auto-generated method stub
		log.debug("finding instace by userId:"+userId+" limit "+number);
		try{
			String queryString = "select model.value from HeartRate as model where model.userId="+userId+" order by model.time desc";
			List lst = ExecuteSQL.executeHQL(getHibernateTemplate(), queryString, number);
			return lst;
		}
		catch(Exception e){
			log.error("find failed");
			e.printStackTrace();
			return null;
		}
	}

}
