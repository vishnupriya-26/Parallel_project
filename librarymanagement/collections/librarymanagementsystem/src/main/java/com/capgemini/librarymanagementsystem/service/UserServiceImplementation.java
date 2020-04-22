package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;

public class UserServiceImplementation implements UserService{

	private UserDAO dao=LibraryFactory.getUserDAO();

	@Override
	public boolean registerUser(User user) {
		return dao.registerUser(user);
	}

	@Override
	public User loginUser(String email, String password) {
		return dao.loginUser(email, password);
	}

	@Override
	public RequestBean bookRequest(User user, Book book) {
		return dao.bookRequest(user, book);
	}

	@Override
	public RequestBean bookReturn(User student, Book book) {
		return dao.bookReturn(student, book);
	}

	@Override
	public LinkedList<Book> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public LinkedList<Book> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public LinkedList<Book> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

	@Override
	public LinkedList<Book> getBooksInfo() {
		return dao.getBooksInfo();
	}

}