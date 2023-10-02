package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Book;
import com.examly.springapp.repository.BookRepo;



@Service
public class BookService{

	@Autowired
	private BookRepo bookRepo;

	public boolean saveBook(Book book) {
		return bookRepo.save(book) != null ? true : false;
	}

	public boolean updateBook(Book book, int bookId) {
			return bookRepo.save(book) != null ? true : false;
	}

	public List<Book> getAllBook() {

		List<Book> list = bookRepo.findAll();
		return list;
	}

	public Book getBookById(int id) {

		if (bookRepo.existsById(id)) {
			Book book = bookRepo.findById(id).get();
			return book;
		}

		return null;
	}

}
