package com.health.service;

import java.util.List;

public interface IHeartRateService {
	public List<Integer> getRecentValues(int userId,int number);
}
