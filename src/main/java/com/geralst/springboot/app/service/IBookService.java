package com.geralst.springboot.app.service;

import java.util.List;
import java.util.Optional;

import com.geralst.springboot.app.entitty.Book;

public interface IBookService {

	public List<Book> getAllBooks();
	
	public Book save(Book book);
	
	public Optional<Book> findById(Long id);
	
	public Book delete(Long id);
	
}
