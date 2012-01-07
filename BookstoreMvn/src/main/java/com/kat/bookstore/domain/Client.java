package com.kat.bookstore.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;

public class Client {
	
	private static Logger logger= Logger.getLogger(Client.class);
	
	public long id;
	public String name = "unknown";
	public String surname = "unknown";
		
	public List<Book> bookList = new ArrayList<Book>();
	
	public Client(){} 
	
	public Client(String name, String surname)
	{
		this.name = name;
		this.surname = surname;
	}
	
	public void printClient() {
		System.out.println(name + " " + surname);
		logger.info("print client: " + name + " " + surname);
	}

	public void printBooks() {
		for (Book b : this.bookList) {
			b.printBook();
		}
		logger.info("print books" );
	}

	public void printAll() {
		System.out.println(name + " " + surname +": "+ bookList.size() + " book(s).");
		for (Book b : this.bookList) {
			b.printBook();
		}
	}

	public void addBook(Book b) {	
		bookList.add(b);
		logger.info("added book \"" +b.title +"\" to client "+ name + " " + surname);
	}

	public void deleteBook(Book book) {
		bookList.remove(book);	
		logger.info("removed book \"" + book.getTitle()+"\" from client "+ name + " " + surname);
	}
	
	public void deleteBooks(List<Book> bookL) {
		
		for (Book b : bookL) {
		bookList.remove(b);	
		}
		logger.info("removed books from "+ name +" " + surname + "'s booklist" );
	}
		
	public void deleteBookList() {
		bookList.clear();
		logger.info("cleared client's ("+name+" "+surname+") booklist");
	}

	public Book findBookTitleAuthor(String title, String author) {
		
		for (Book book : bookList) {
			if (book.getTitle().equals(title) && book.getAuthor().equals(author))					 
			{
				return book;
			}
			logger.info("found a book \"" + title +"\"" + "by "+ author);
		}
		return null;
	}
	
	public List<Book> findBooksGenre(BookGenre genre) {
		List<Book> results= new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.getGenre().equals(genre)) {
				results.add(book);
			}
			logger.info("found all books from genre " + genre );
		}
		return results;
	}
	
	public List<Book> findBooksAuthor(String author) {
		List<Book> results= new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.getAuthor().equals(author)) {
				results.add(book);
			}
			logger.info("found books by " + author );
		}
		return results;
	}
	
	public List<Book> findBooksTitle(String title) {
		List<Book> results= new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				results.add(book);
			}
			logger.info("found all books with title \"" + title + "\""  );
		}
		return results;
	}

	@Size(min = 2, max = 20)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		logger.info("set client's name" + name);
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
		logger.info("set client's surname" + surname);
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
		logger.info("set booklist" );
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Client.logger = logger;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}