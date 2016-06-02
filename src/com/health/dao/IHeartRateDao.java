package com.health.dao;

import java.util.List;
import com.health.entity.HeartRate;

public interface IHeartRateDao {
	public List<Integer> findByProperty(int userId,int number);
	public void save(HeartRate transientInstance);
}
