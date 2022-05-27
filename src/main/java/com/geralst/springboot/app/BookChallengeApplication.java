package com.geralst.springboot.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.geralst.springboot.app.entitty.Book;
import com.geralst.springboot.app.repository.BookRepository;

@SpringBootApplication
public class BookChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookChallengeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(BookRepository bookRepository) {
		return (args) -> {

		
			Book book1 = new Book((long) 1,"La pandilla de asakusa", "Yasunari Kawabata", "emecé");
			Book book2 = new Book((long) 2,"Don Gato", "Maria Gomez", "emecé");

			bookRepository.save(book1);
			bookRepository.save(book2);
		};
	}
}

