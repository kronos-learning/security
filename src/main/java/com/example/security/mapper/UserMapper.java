package com.example.security.mapper;

import java.util.List;

import com.example.security.entity.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public User findByLoginIdAndPassword(String loginId, String password);
	
	public void update(User user);
}
