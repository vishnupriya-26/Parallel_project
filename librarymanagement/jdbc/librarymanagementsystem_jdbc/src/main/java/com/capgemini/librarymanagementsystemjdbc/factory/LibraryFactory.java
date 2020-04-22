package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.service.UserServiceImplementation;

public class LibraryFactory {
	public static UserDAO getUsersDao() {
		return new UserDAOImplementation();
	}
	public static UserService getUsersService() {
		return new UserServiceImplementation();
	}

}
