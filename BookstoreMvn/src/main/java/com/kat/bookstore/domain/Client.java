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
	
	//public Client(){} 
	
	public Client(String name, String surname)
	{
		this.name = name;
		this.surname = surname;
		this.bookList = new ArrayList<Book>();
	}
	
	public void printClient() {
		System.out.println("------"+ this.name + " " + this.surname+"------");
		logger.info("print client: " + this.name + " " + this.surname);
	}

	public void printBooks() {
		for (Book b : this.bookList) {
			b.printBook();
		}
		logger.info("print client's ("+this.name+" "+this.surname+") booklist " );
	}

	public void addBook(String title, String author, BookGenre genre, int year,
			int price) throws PriceBelowZeroException {
		if (price > 0) {
			bookList.add(new Book(title, author, genre, year, price));
			logger.info("Book: " + title + " - added");
		}
		if (price <= 0)
			throw new PriceBelowZeroException("Price cannot by less than 0");
	}
	
	public void deleteBook(List<Book> bookL) {
		
		for (Book b : bookL) {
		bookList.remove(b);	
		}
		logger.info("removed from "+ this.name +" " + this.surname + "'s booklist" );
	}
		
	public void deleteBookList() {
		bookList.clear();
		logger.info("cleared client's ("+name+" "+surname+") booklist");
	}

	public void editBookPrice(List<Book> list, int price) throws PriceBelowZeroException {
		if (price > 0) {
			for (Book b : list) {
				b.setPrice(price);
				logger.info("Prize edit in " + b.getTitle() + ", new price: "
						+ price);
			}
		}
		if (price <= 0)
			throw new PriceBelowZeroException("Price cannot by less than 0");
	}
	
	public void editBookYear(List<Book> list, int year) {
		for (Book b : list) {
			b.setYear(year);
			logger.info("Release year edit in " + b.getTitle()
					+ ", new release year: " + year);
		}
	}
	
	public void editBookGenre(List<Book> list, BookGenre genre) {
		for (Book b : list) {
			b.setGenre(genre);
			logger.info("Game type edit in " + b.getTitle()
					+ ", new game type: " + genre);
		}
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
	
	public List<Book> findBooksYear(int year) {
		List<Book> results = new ArrayList<Book>();
		for (Book b : bookList) {
			if (b.getYear()==year) {
				results.add(b);	
			}
			logger.info("found books by " + year );
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
