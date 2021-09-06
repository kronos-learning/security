package com.example.security.service;

import java.util.List;

import com.example.security.entity.User;
import com.example.security.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public User findById(int id) {
		return userMapper.findById(id);
	}	
	
	@Override
	public User findByIdAndPassword(String loginId, String password) {
		return userMapper.findByLoginIdAndPassword(loginId, password);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}
}
