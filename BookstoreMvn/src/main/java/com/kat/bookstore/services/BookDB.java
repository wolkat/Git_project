package com.kat.bookstore.services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.kat.bookstore.domain.*;


public class BookDB {

	private Connection connect;
	private Statement statement;
	private PreparedStatement addBookStmt;
	private PreparedStatement getBookStmt;
	private PreparedStatement deleteBookStmt;
	private PreparedStatement deleteAllBooksStmt;
	private PreparedStatement findAllBooksByTitleStmt;
	private PreparedStatement findAllBooksByGenreStmt;
	
	List<Integer> listID = new ArrayList<Integer>();
	
	public BookDB() 
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
			boolean BookTableExists = false;

			ResultSet set = connect.getMetaData().getTables(null, null, null, null);

			while (set.next()) 
			{
				if ("Book".equalsIgnoreCase(set.getString("TABLE_NAME"))) 
				{
					BookTableExists = true;
					break;
				}
			}

			if (!BookTableExists) 
			{
				statement.executeUpdate("CREATE TABLE Book(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, title varchar(40), author varchar(40), genre varchar(20), releaseYear integer, price integer)");
			}


			addBookStmt = connect.prepareStatement("INSERT INTO Book (title, author, genre, price) VALUES (?, ?, ?, ?)");

			getBookStmt = connect.prepareStatement("SELECT * FROM Book");
			
			deleteAllBooksStmt = connect.prepareStatement("DELETE FROM Book");
			
			findAllBooksByTitleStmt = connect.prepareStatement("SELECT id FROM Book WHERE title = ?");
			
			findAllBooksByGenreStmt = connect.prepareStatement("SELECT id FROM Book WHERE genre = ?");
			
			deleteBookStmt = connect.prepareStatement("DELETE FROM Book WHERE id = ?");
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void addBook(Book b) 
	{
		try 
		{
			addBookStmt.setString(1, b.getTitle());
			addBookStmt.setString(2, b.getAuthor());
			addBookStmt.setString(3, b.getGenre().toString());
			addBookStmt.setInt(4, b.getPrice());
			addBookStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}

	public List<Book> getAllBooks() 
	{
		List<Book> Books = new ArrayList<Book>();
		try 
		{
			ResultSet set = getBookStmt.executeQuery();
			while (set.next()) 
			{
				BookGenre genre = null;
				if (set.getString("genre").equals("Drama")) genre = BookGenre.Drama;
				if (set.getString("genre").equals("Fantasy")) genre = BookGenre.Fantasy;
				if (set.getString("genre").equals("Horror")) genre = BookGenre.Horror;
				if (set.getString("genre").equals("Mistery")) genre = BookGenre.Mistery;
				if (set.getString("genre").equals("Romance")) genre = BookGenre.Romance;
				
				Books.add(new Book(set.getString("title"),set.getString("author"),genre,set.getInt("price")));
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Books;
	}

	public void deleteAllBooks() 
	{
		try 
		{
			deleteAllBooksStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<Integer> findBookByTitle(String name)
	{
		try 
		{
			List<Integer> result = new ArrayList<Integer>();
			findAllBooksByTitleStmt.setString(1, name);
			ResultSet set = findAllBooksByTitleStmt.executeQuery();
			while (set.next())
				result.add(set.getInt("ID"));	
			return result;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Integer> findBookByGenre(BookGenre genre)
	{
		try 
		{
			List<Integer> result = new ArrayList<Integer>();
			findAllBooksByGenreStmt.setString(1, genre.toString());
			ResultSet set = findAllBooksByGenreStmt.executeQuery();
			while (set.next())
				result.add(set.getInt("ID"));
			return result;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteBook(List<Integer> list)
	{
		try 
		{
			for (Integer id : list)
			{
				deleteBookStmt.setInt(1, id);
				deleteBookStmt.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void printBookCondition(List<Book> listBook,Condition condition)
	{
		for (Book b : listBook)
		{
			if (condition.getCondition(b))
			{
				System.out.println("Title: " + b.getTitle() + "\tAuthor: " + b.getAuthor() + "\tgenre: " + b.getGenre() + "\tPrice: " + b.getPrice());
			}
		}
	}
	


}