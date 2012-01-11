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
		this.bookList = new ArrayList<Book>();
	}
	
	public void printClient() {
		System.out.println(this.name + " " + this.surname);
		logger.info("print client: " + this.name + " " + this.surname);
	}

	public void printBooks() {
		for (Book b : this.bookList) {
			b.printBook();
		}
		logger.info("print client's ("+this.name+" "+this.surname+") booklist " );
	}
	
	public void printBookList(List<Book> tmpList) {
		for (Book b : tmpList)
			b.printBook();
	}

	public void addBook(Book b) {	
		bookList.add(b);
		logger.info("added book \"" +b.title +"\" to client "+ name + " " + surname);
	}

	public void deleteBook(Book b) {
		bookList.remove(b);	
		logger.info("removed book \"" + b.getTitle()+"\" from client "+ name + " " + surname);
	}
	
	public void deleteBooks(List<Book> bookL) {
		
		for (Book b : bookL) {
		bookList.remove(b);	
		}
		logger.info("removed books from "+ this.name +" " + this.surname + "'s booklist" );
	}
		
	public void deleteBookList() {
		bookList.clear();
		logger.info("cleared client's ("+name+" "+surname+") booklist");
	}

	public List<Book> findBooksGenre(BookGenre genre) {
		List<Book> results= new ArrayList<Book>();
		for (Book b : bookList) {
			if (b.getGenre().equals(genre)) {
				results.add(b);
			}
			logger.info("found all books from genre: " + genre );
		}
		return results;
	}
	
	public List<Book> findBooksAuthor(String author) {
		List<Book> results= new ArrayList<Book>();
		for (Book b : bookList) {
			if (b.getAuthor().equals(author)) {
				results.add(b);
			}
			logger.info("found books by " + author );
		}
		return results;
	}
	
	public List<Book> findBooksTitle(String title) {
		List<Book> results= new ArrayList<Book>();
		for (Book b : bookList) {
			if (b.getTitle().equals(title)) {
				results.add(b);
			}
			logger.info("found all books with title \"" + title + "\""  );
		}
		return results;
	}
	
	public void removeAllBooksByList(List<Book> tempBookList) {
		for (Book b : bookList) {
			for (Book tempB : tempBookList) {
				if (b.equals(tempB)) {
					bookList.remove(b);
				}
			}
		logger.info("removed all books from list \"" + tempBookList + "\""  );
		}
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
