package com.example.security.service;

import java.util.List;

import com.example.security.entity.Book;
import com.example.security.mapper.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookMapper bookMapper;
	
	@Override
	public List<Book> findAll() {
		return bookMapper.findAll();
	}

	@Override
	public Book findById(int id) {
		return bookMapper.findById(id);
	}

	@Override
	public void update(Book book) {
		bookMapper.update(book);
	}
}
