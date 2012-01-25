package com.kat.bookstore.services;


import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.kat.bookstore.domain.*;


public class ClientBookDB {

	private Connection connect;
	private Statement statement;
	private PreparedStatement addBookToClientStmt;
	private PreparedStatement deleteAllClientBookStmt;
	private PreparedStatement deleteAllBooksFromClientStmt;
	private PreparedStatement getClientBookStmt;

	public ClientBookDB() 
	{
		try 
		{
			Properties props = new Properties();
			
			try {
				props.load(ClassLoader.getSystemResourceAsStream("com/kat/bookstore/jdbs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			connect = DriverManager.getConnection(props.getProperty("url"));

			statement = connect.createStatement();
			boolean TableExists = false;

			ResultSet set = connect.getMetaData().getTables(null, null, null, null);

			while (set.next()) 
			{
				if ("ClientBook".equalsIgnoreCase(set.getString("TABLE_NAME"))) 
				{
					TableExists = true;
					break;
				}
			}

			if (!TableExists) 
			{
				statement.executeUpdate("CREATE TABLE ClientBook(client_id int, book_id int, CONSTRAINT client_id_fk FOREIGN KEY (client_id) REFERENCES Client (id), CONSTRAINT book_id_fk FOREIGN KEY (book_id) REFERENCES Book (id))");
			}
			
			addBookToClientStmt = connect.prepareStatement("INSERT INTO ClientBook (client_id, book_id) VALUES (?, ?)");
			
			deleteAllBooksFromClientStmt = connect.prepareStatement("DELETE FROM ClientBook WHERE client_id = ?");
			
			deleteAllClientBookStmt = connect.prepareStatement("DELETE FROM ClientBook");
			
			getClientBookStmt = connect.prepareStatement("SELECT * FROM Book, ClientBook WHERE client_id = ? and book_id = Book.id");

		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}
	
	
	public void addBookToClient(List<Integer> clientIDlist, List<Integer> bookIDlist) 
	{
		try 
		{
			for (Integer clientID : clientIDlist)
			{
				for (Integer bookID : bookIDlist)
				{
					addBookToClientStmt.setInt(1, clientID);
					addBookToClientStmt.setInt(2, bookID);
					addBookToClientStmt.executeUpdate();
				}
			}
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}
	
	public void deleteAllBooksFromClient (List<Integer> clientIDlist) 
	{
		try 
		{
			for (Integer clientID : clientIDlist)
			{
					deleteAllBooksFromClientStmt.setInt(1, clientID);
					deleteAllBooksFromClientStmt.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}
	
	public void deleteAllClientBook () 
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
	
	public List<Book> getClientBook (List<Integer> clientIDlist)
	{
		List<Book> booklist = new ArrayList<Book>();
		try 
		{
			for (Integer clientID : clientIDlist)
			{
				getClientBookStmt.setInt(1, clientID);
				ResultSet set = getClientBookStmt.executeQuery();
				while (set.next()) 
				{
					BookGenre genre = null;
					if (set.getString("genre").equals("Drama")) genre = BookGenre.Drama;
					if (set.getString("genre").equals("Fantasy")) genre = BookGenre.Fantasy;
					if (set.getString("genre").equals("Horror")) genre = BookGenre.Horror;
					if (set.getString("genre").equals("Mistery")) genre = BookGenre.Mistery;
					if (set.getString("genre").equals("Romance")) genre = BookGenre.Romance;
					
					booklist.add(new Book(set.getString("title"),set.getString("author"),genre,set.getInt("price")));
				}
			}
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return booklist;
	}
}
