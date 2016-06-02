package com.health.service;

import java.util.List;

import com.health.entity.User;
public interface IUserService {
	public User login(String userName,String password);
	public User createUser(String userName,String  password,
			String realName,String phoneNumber, String gender,String birthday
			);

	public void deleteUser(Integer userId);
	public Integer getIdByUserName(String userName);

	public Long countUser(String userNameTop);

	public List<User> loadUserList(Integer start, Integer limit,
			String userNameTop);

	public User loadOneUser(Integer userId);
	public User loadOneUser(String string);

	//public List<Systemrole> getAllSystemrole();
}
