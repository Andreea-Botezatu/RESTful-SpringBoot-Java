package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	
}
