package com.reavature.dao;

import java.util.List;

import com.reavature.driver.User;

public interface  UserDao {
	
	
	
	List<User> getUers();
	public User getUserById(int id);
	public int CreateUser(User uRef);
	public int updateDepartment(User uRef);
	public int deleteDepartment(int id);	

}
