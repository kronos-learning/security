package com.example.security.mapper;

import java.util.List;

import com.example.security.entity.Book;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

	public List<Book> findAll();
	
	public Book findById(int id);
	
	public void update(Book book);
}
