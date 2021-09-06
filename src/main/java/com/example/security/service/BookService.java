package com.example.security.service;

import java.util.List;

import com.example.security.entity.Book;

public interface BookService {
	
	public List<Book> findAll();
	
	public Book findById(int id);
	
	public void update(Book book);
}
