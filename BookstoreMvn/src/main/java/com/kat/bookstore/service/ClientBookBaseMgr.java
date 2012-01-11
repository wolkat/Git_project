package com.kat.bookstore.service;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.kat.bookstore.domain.Book;
import com.kat.bookstore.domain.Client;


public class ClientBookBaseMgr {

	private Connection connect;
	private Statement statement;
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private PreparedStatement addBookClientStmt;
	private PreparedStatement deleteBooksFromClientStmt;
	private PreparedStatement getClientBookStmt;
	private PreparedStatement deleteAllClientBookStmt;
	
	public ClientBookBaseMgr() {
		try {
			
			
			connect = DriverManager.getConnection(url);

			statement = connect.createStatement();
			boolean ClientBookTableExists = false;

			ResultSet rs = connect.getMetaData().getTables(null, null, null, null);

			while (rs.next()) {
				if ("ClientBook".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					ClientBookTableExists = true;
					break;
				}
			}

			if (!ClientBookTableExists) {
				statement.executeUpdate("CREATE TABLE ClientBook(client_id bigint, book_id bigint, FOREIGN KEY (client_id) REFERENCES Client (id),"
						+ " FOREIGN KEY (book_id) REFERENCES Book (id))");
			}

			addBookClientStmt = connect.prepareStatement("INSERT INTO ClientBook (client_id, book_id) VALUES (?, ?)");

			deleteBooksFromClientStmt = connect.prepareStatement("DELETE FROM ClientBook WHERE client_id = ?");

			deleteAllClientBookStmt = connect.prepareStatement("DELETE FROM ClientBook");

			getClientBookStmt = connect
					.prepareStatement("SELECT book.title, book.author FROM Book,"
							+ " ClientBook WHERE client_id = ? and book_id = book.id");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void addBooksClient(List<Integer> ClientList,
			List<Integer> BookList) {
		try {
			for (Integer clientID : ClientList) {
				for (Integer bookID : BookList) {
					addBookClientStmt.setInt(1, clientID);
					addBookClientStmt.setInt(2, bookID);
					addBookClientStmt.executeUpdate();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	
	public void deleteAll() {
		try {
			deleteBooksFromClientStmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public void getClientBooks(List<Integer> clientList, List<Integer> bookList) {
		
		try {
			for (Integer clientID : clientList) {
				for (Integer bookID: bookList) {
					addBookClientStmt.setInt(1, clientID);
					addBookClientStmt.setInt(2, bookID);
					addBookClientStmt.executeUpdate();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}