package com.example.security.service;

import java.util.List;

import com.example.security.entity.User;

public interface UserService {

	public List<User> findAll();
	
	public User findById(int id);
	
	public User findByIdAndPassword(String loginId, String password);
	
	public void update(User user);

}
