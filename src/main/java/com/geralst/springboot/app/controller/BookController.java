package com.geralst.springboot.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.geralst.springboot.app.entitty.Book;
import com.geralst.springboot.app.repository.BookRepository;
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
	
	@PostMapping()
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book newBook = bookService.save(book);
		return ResponseEntity.ok(newBook);
	}
	
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
