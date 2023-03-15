package com.springboot.app.service;

import java.util.List;

import com.springboot.app.model.Book;

public interface BookService {

	Book saveBook(Book book);
	List<Book> getAllBooks();
	List<Book> searchByTitle(String title);
	Book updateBookAuthor(long id, String author);
	void deleteBook(long id);
}
