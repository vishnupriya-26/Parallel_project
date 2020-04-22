package com.capgemini.librarymanagementsystem.dao;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.db.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class UserDAOImplementation implements UserDAO{


	@Override
	public boolean registerUser(User user) {
		for(User u : LibraryDB.USER) {
			if(u.getEmail().equals(user.getEmail())) {
				return false;
			}
		}
		LibraryDB.USER.add(user);
		return true;
	}

	@Override
	public User loginUser(String email, String password){
		for(User user : LibraryDB.USER) {
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		throw new LMSException("Invalid Credentials");
	}


	@Override
	public LinkedList<Book> searchBookByTitle(String bookName) {
		LinkedList<Book> searchList=new LinkedList<Book>();
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			Book retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBookName=retrievedBook.getBookName();
			if(bookName.equals(retrievedBookName))
			{
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if(searchList.size()==0)
		{
			throw new LMSException ("Book is not found");
		}
		else
		{
			return searchList;
		}

	}

	@Override
	public LinkedList<Book> searchBookByAuthor(String author) {
		LinkedList<Book> searchList=new LinkedList<Book>();
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			Book retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBookAuthor=retrievedBook.getAuthorName();
			if(author.equals(retrievedBookAuthor))
			{
				searchList.add(retrievedBook);	
			}
		}
		if(searchList.size()==0)
		{
			throw new LMSException ("Book is not found");
		}
		else
		{
			return searchList;
		}	
	}

	@Override
	public LinkedList<Book> searchBookByCategory(String category) {
		LinkedList<Book> searchList=new LinkedList<Book>();
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			Book retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedCategory=retrievedBook.getBookCategory();
			if(category.equals(retrievedCategory))
			{
				searchList.add(retrievedBook);	
			}
		}
		if(searchList.size()==0)
		{
			throw new LMSException("Book not found");
		}
		else
		{
			return searchList;
		}	

	}

	@Override
	public LinkedList<Book> getBooksInfo() {
		return LibraryDB.BOOKS;
	}

	@Override
	public RequestBean bookRequest(User user, Book book) {
		boolean flag = false, 
				isRequestExists = false;
		RequestBean requestInfo = new RequestBean();
		User userInfo2 = new User();
		Book bookInfo2 = new Book();

		for (RequestBean requestInfo2 : LibraryDB.REQUEST) {
			if (book.getBookId() == requestInfo2.getBookInfo().getBookId()) {
				isRequestExists = true;

			}

		}

		if (!isRequestExists) {
			for (User userBean : LibraryDB.USER) {
				if (user.getId() == userBean.getId()) {
					for (Book book1 : LibraryDB.BOOKS) {
						if (book1.getBookId() == book1.getBookId()) {
							userInfo2 = userBean;
							bookInfo2 = book1;
							flag = true;
						}
					}
				}
			}
			if (flag == true) {
				requestInfo.setBookInfo(bookInfo2);
				requestInfo.setUserInfo(userInfo2);;
				LibraryDB.REQUEST.add(requestInfo);
				return requestInfo;
			}

		}

		throw new LMSException("Invalid request or you cannot request that book");

	}

	@Override
	public RequestBean bookReturn(User user, Book book) {
		for (RequestBean requestInfo : LibraryDB.REQUEST) {

			if (requestInfo.getBookInfo().getBookId() == book.getBookId() &&
					requestInfo.getUserInfo().getId() == user.getId() &&
					requestInfo.isIssued() == true) {


				System.out.println("Returning Issued book only");
				requestInfo.setReturned(true);


				return requestInfo;
			}

		}

		throw new  LMSException("Invalid return ");
	}

}