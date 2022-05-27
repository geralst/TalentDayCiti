package com.geralst.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geralst.springboot.app.entitty.Book;
import com.geralst.springboot.app.repository.BookRepository;

@Service
public class BookServiceImpl implements IBookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book save (Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id);
	}

	@Override
	public Book delete(Long id) {
		bookRepository.deleteById(id);
		return null;
	}

	
}
