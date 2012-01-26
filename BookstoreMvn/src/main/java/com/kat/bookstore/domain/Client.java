package com.kat.bookstore.domain;

import java.util.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.apache.log4j.*;

public class Client {

	private static Logger logger = Logger.getLogger(Client.class);

	String name = "unknown";
	List<Book> booklist = new ArrayList<Book>();
	Date dateOfBirth = new Date();
	

	public Client(String name, Date dateOfBirth) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.booklist = new ArrayList<Book>();
	}

	public void printBook() {
		for (Book b : booklist) {
			b.printBook();
		}
	}

	public void printClient() {
		System.out.println("==== " + name + " ====");
	}

	public void printAll() {
		printClient();
		System.out.println();
		printBook();
		System.out.println();
	}

	public void addBook(String title, String author, BookGenre genre, int price) throws PriceBelowZeroException {
		if (price > 0) {
			booklist.add(new Book(title, author, genre, price));
			logger.info("Book:" + title + " - added");
		}
		if (price <= 0)
			throw new PriceBelowZeroException("Price cannot by less than 0");
	}

	public void deleteBook(List<Book> list) {
		for (Book b : list) {
		booklist.remove(b);
		logger.info("Book: " + b + " - removed");
		}
	}

	public void deleteAllBooks() {
		booklist.clear();
		logger.info("Removed all books");
	}

	public void editBookPrice(List<Book> list, int price) throws PriceBelowZeroException {
		if (price > 0) {
			for (Book b : list) {
				b.setPrice(price);
				logger.info("Price edit in " + b.getTitle() + ", new price: "+ price);
			}
		}
		if (price <= 0)
			throw new PriceBelowZeroException("Price cannot by less than 0");
	}

	public void editBookGenre(List<Book> list, BookGenre genre) {
		for (Book b : list) {
			b.setGenre(genre);
			logger.info("Book genre edit in " + b.getTitle()
					+ ", new genre: " + genre);
		}
	}

	public List<Book> findAllBooksByTitle(String title) {
		List<Book> results = new ArrayList<Book>();
		for (Book game : booklist) {
			if (game.getTitle().equals(title)) {
				results.add(game);
			}
		}
		return results;
	}

	public List<Book> findAllBooksByGenre(BookGenre genre) {
		List<Book> results = new ArrayList<Book>();
		for (Book b : booklist) {
			if (b.getGenre().equals(genre)) {
				results.add(b);
			}
		}
		return results;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Client.logger = logger;
	}

	@Size(min=1, max=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

	@Past
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
