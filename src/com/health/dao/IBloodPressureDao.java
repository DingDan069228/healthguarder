package com.health.dao;

import java.util.List;

import com.health.entity.BloodPressure;

public interface IBloodPressureDao {
	public List<Integer> findByProperty(int id,int number);
	public void save(BloodPressure transientInstance);
}
