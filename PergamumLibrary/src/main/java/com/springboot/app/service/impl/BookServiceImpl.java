package com.springboot.app.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.app.model.Book;
import com.springboot.app.repository.BookRepository;
import com.springboot.app.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> sortedBooks = bookRepository.findAll();
		Comparator<Book> byAuthorThenTitle = Comparator
				.comparing(Book::getAuthor)
				.thenComparing(Book::getTitle);
		return sortedBooks.stream()
				.sorted(byAuthorThenTitle)
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> searchByTitle(String title) {
		List<Book> books = bookRepository.findAll();
		return  books.stream()
	            .filter(book -> book.getTitle().equals(title))
	            .collect(Collectors.toList());
	}

	@Override
	public Book updateBookAuthor(long id, String author) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAuthor(author);
            return bookRepository.save(book);
        }
        return null;
	//throw custom exception?
	}

	@Override
	public void deleteBook(long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			bookRepository.deleteById(id);
		}
	}


}
