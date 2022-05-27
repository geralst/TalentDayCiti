package com.geralst.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geralst.springboot.app.entitty.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
