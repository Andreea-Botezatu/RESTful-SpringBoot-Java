package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Book;
import com.springboot.app.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	//create book
	@PostMapping()
	public ResponseEntity<Book> saveBook(@RequestBody Book book){
		return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED);
	}

	//get all books, sorted
	@GetMapping
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}

	//get a book by title (or multiple)
	@GetMapping(params = "title")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam("title") String title) {
        //List<Book> books = bookService.searchByTitle(title);
        return new ResponseEntity<List<Book>>(bookService.searchByTitle(title), HttpStatus.OK);
    }
	
	//update a book with a new author (by ID? in case multiple books have the same author/title)
	@PutMapping("/{id}")
    public ResponseEntity<Book> updateAuthor(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBookAuthor(id, book.getAuthor());
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
	
	//delete a book :(
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") long id){
		bookService.deleteBook(id);
		return new ResponseEntity<String>("Book deleted", HttpStatus.OK);
		
	}

}
