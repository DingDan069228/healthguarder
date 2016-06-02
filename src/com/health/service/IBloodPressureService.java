package com.health.service;

import java.util.List;

public interface IBloodPressureService {
	public List<Integer> getRecentValues(int userId,int number);
}
