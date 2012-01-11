package com.kat.bookstore.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.kat.bookstore.domain.*;

public class BooksBaseMgr {
	
	private Connection connect;
	private Statement statement;
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private String createTableBook = "CREATE TABLE Book(id bigint GENERATED BY DEFAULT AS IDENTITY," +
										"title varchar(20), author varchar(20))";
	private PreparedStatement addBookStmt;
	private PreparedStatement deleteBookStmt;
	private PreparedStatement deleteAllBooksStmt;
	private PreparedStatement getAllBooksStmt;
	private PreparedStatement getAllBooksIDStmt;
	private PreparedStatement findBookByTitleStmt;
	private PreparedStatement findBookByAuthorStmt;
	List<Integer> IDList = new ArrayList<Integer>();
	
	
	public BooksBaseMgr() {
	try {
		connect = DriverManager.getConnection(url);
		statement = connect.createStatement();

		ResultSet rs = connect.getMetaData().getTables(null, null, null, null);
		boolean tableExists = false;
		
		while (rs.next()) {
			if ("Book".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
				tableExists = true;
				break;
			} 
		}
	

		if (!tableExists) statement.executeUpdate(createTableBook);

		addBookStmt = connect.prepareStatement("INSERT INTO Book (title, author) VALUES (?, ?)");
		findBookByTitleStmt = connect.prepareStatement("SELECT id FROM Book WHERE title= ?");
		findBookByAuthorStmt = connect.prepareStatement("SELECT id FROM Book WHERE author= ?");
		deleteBookStmt = connect.prepareStatement("DELETE FROM Book WHERE id = ?");
		deleteAllBooksStmt = connect.prepareStatement("DELETE FROM Book");
		getAllBooksStmt = connect.prepareStatement("SELECT id, title, author FROM Book");
		getAllBooksIDStmt = connect.prepareStatement("SELECT id from Book");
	} catch (SQLException e) {
		e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connect;
	}

	void clearAllBooks() {
		try {
			deleteAllBooksStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addBook(Book b) {
		int count = 0;
		try {
			addBookStmt.setString(1, b.getTitle());
			addBookStmt.setString(2, b.getAuthor());

			count = addBookStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	

	public List<Book> getAllBooks() {
		List<Book> booklist = new ArrayList<Book>();

		try {
			ResultSet rs = getAllBooksStmt.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				booklist.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booklist;
	}
	
	public void deleteAllBooks() {
		try {
			deleteAllBooksStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> getAllBooksID() {
		List<Integer> bookList = new ArrayList<Integer>();
		
		try {
			ResultSet rs = getAllBooksIDStmt.executeQuery();
			while (rs.next())
				bookList.add(rs.getInt("ID"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public List<Integer> findBookByTitle(String title) {
		try {
			List<Integer> bookList = new ArrayList<Integer>();
			findBookByTitleStmt.setString(1, title);
			ResultSet rs = findBookByTitleStmt.executeQuery();
			while (rs.next())
				bookList.add(rs.getInt("ID"));
			return bookList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Integer> findBookByAuthor(String author) {
		try {
			List<Integer> result = new ArrayList<Integer>();
			findBookByAuthorStmt.setString(1, author);
			ResultSet rs = findBookByAuthorStmt.executeQuery();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteBook(List<Integer> bookList) {
		try {
			for (Integer id : bookList) {
				deleteBookStmt.setInt(1, id);
				deleteBookStmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/*
	// klasa anonimowa
	public void printBookWithCondition(List<Book> BookList, Condition condition) {
		for (Book book : BookList) {
			if (condition.getCondition(book)) {
				System.out.println("Name: " + book.getName() + "\nAuthor: "
						+ book.getAuthor());
			}
		}
	}*/
}
