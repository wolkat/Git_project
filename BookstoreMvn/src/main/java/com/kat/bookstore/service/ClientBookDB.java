package com.kat.bookstore.service;

import com.kat.bookstore.domain.*;

import java.sql.*;
import java.util.*;


public class ClientBookDB {

	private Connection connect;
	private Statement statement;
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private PreparedStatement addBooktoClientStmt;
	private PreparedStatement deleteBooksFromClientStmt;
	private PreparedStatement getBooksClientStmt;
	private PreparedStatement deleteAllClientBookStmt;
	
	public ClientBookDB() {
		try 
		{
					
			connect = DriverManager.getConnection(url);

			statement = connect.createStatement();
			boolean ClientBookTableExists = false;

			ResultSet set = connect.getMetaData().getTables(null, null, null, null);

			while (set.next()) {
				if ("ClientBook".equalsIgnoreCase(set.getString("TABLE_NAME"))) {
					ClientBookTableExists = true;
					break;
				}
			}

			if (!ClientBookTableExists) {
				statement.executeUpdate("CREATE TABLE ClientBook(client_id bigint, book_id bigint, FOREIGN KEY (client_id) REFERENCES Client (id),"
						+ " FOREIGN KEY (book_id) REFERENCES Book (id))");
			}

			addBooktoClientStmt = connect.prepareStatement("INSERT INTO ClientBook (client_id, book_id) VALUES (?, ?)");
			deleteBooksFromClientStmt = connect.prepareStatement("DELETE FROM ClientBook WHERE client_id = ?");
			deleteAllClientBookStmt = connect.prepareStatement("DELETE FROM ClientBook");
			getBooksClientStmt = connect.prepareStatement("SELECT * FROM Book, ClientBook WHERE client_id = ? and book_id = Book.id");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void addBooksToClient(List<Integer> ClientList,
			List<Integer> BookList) {
		try {
			for (Integer clientID : ClientList) {
				for (Integer bookID : BookList) {
					addBooktoClientStmt.setInt(1, clientID);
					addBooktoClientStmt.setInt(2, bookID);
					addBooktoClientStmt.executeUpdate();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	
	public void deleteBookFromClient(List<Integer> clientIDlist) {
		try {
			for (Integer clientID : clientIDlist)
			{
				deleteBooksFromClientStmt.setInt(1, clientID);
				deleteBooksFromClientStmt.executeUpdate();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public void deleteAll() 
	{
		try 
		{
			deleteAllClientBookStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	
	public void getClientBooks(List<Integer> clientList) {
		List<Book> bookList = new ArrayList<Book>();
		try {
			for (Integer clientID : clientList) {
				getBooksClientStmt.setInt(1, clientID);
				ResultSet set = getBooksClientStmt.executeQuery();
				while (set.next()) 
				{
					BookGenre genre = null;
					if (set.getString("genre").equals("Horror")) genre= BookGenre.Horror;
					if (set.getString("genre").equals("Romance")) genre= BookGenre.Romance;
					if (set.getString("genre").equals("Fantasy")) genre= BookGenre.Fantasy;
					if (set.getString("genre").equals("Mistery")) genre= BookGenre.Mistery;
					if (set.getString("genre").equals("Drama")) genre= BookGenre.Drama;
					
					bookList.add(new Book(set.getString("title"),set.getString("author"),genre,set.getInt("year"),set.getInt("price")));
				
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}