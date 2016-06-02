package com.health.dao;

import java.util.List;

import com.health.entity.User;

public interface IUserDao {
	public List findByProperty(String propertyName, Object value);

	public void save(User transientInstance);

	public List<User> findByUserName(String userName);

	public User findById(java.lang.Integer id);

	public List findAll();

	public void attachDirty(User instance);
	
	public List<User> findByRealName(String realName);
	
	public User updateByUserName(User user,String originalUserName);
}
