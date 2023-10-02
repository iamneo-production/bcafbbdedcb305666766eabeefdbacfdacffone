package com.examly.springapp.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Book;
import com.examly.springapp.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody Book book ) {

		boolean s = bookService.saveBook(book);
		if (s) {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<Boolean> update(@RequestBody Book book , @PathVariable int bookId) {

		boolean s = bookService.updateBook(book, bookId);
		if (s) {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(s, HttpStatus.NOT_FOUND);
	}


	@GetMapping
	public ResponseEntity<List<Book>> getAll() {

		List<Book> s = bookService.getAllBook();
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getById(@PathVariable int bookId) {

		Book book = bookService.getBookById(bookId);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

}
