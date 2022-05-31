package com.geralst.springboot.app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.geralst.springboot.app.entitty.Book;
import com.geralst.springboot.app.exception.RequestException;
import com.geralst.springboot.app.service.IBookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping()
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}
	
	@RequestMapping(value="{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
		Optional<Book> bookId = bookService.findById(id);
		if(bookId.isPresent()) {
			return ResponseEntity.ok(bookId.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> createBook(@RequestBody Book book) {
		Map<String,Object> response = new HashMap<>();
		if(book.getTitle().equals("") || book.getTitle() == null) {
			throw new RequestException("200","Email is required");
		}
		response.put("message", "The Book was successfully added to the data base");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	/* Una forma de personalizar las excepciones
	@PostMapping("/guardar")
	public ResponseEntity<?> createBook(@RequestBody Book book) {
		Map<String,Object> response = new HashMap<>();
		
		try {
			bookService.save(book);
			response.put("message", "The Book was successfully added to the data base");
		} catch (DataAccessException e) {
			response.put("message", "Error inserting book into database");
			response.put("error",e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	*/
	@DeleteMapping(value= "{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
		bookService.delete(id);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping()
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
		Optional<Book>  bookId = bookService.findById(book.getId());
		if(bookId.isPresent()) {
			Book updateBook = bookId.get();
			updateBook.setTitle(book.getTitle());
			updateBook.setAuthor(book.getAuthor());
			updateBook.setEditorial(book.getEditorial());
			bookService.save(updateBook);
			return ResponseEntity.ok(updateBook);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
