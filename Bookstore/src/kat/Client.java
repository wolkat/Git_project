package kat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class Client {
	
	private static Logger logger= Logger.getLogger(Client.class);
	

	public String name;
	public String surname;
		
	public List<Book> bookList = new ArrayList<Book>();
	 Client(String name, String surname)
	{
		this.name = name;
		this.surname = surname;
	}
	
	public Client(String name, String surname, List<Book> bookList)
	{
		this.name=name;
		this.surname=surname;
		this.bookList=bookList;
	}
	
	public String toString()
	{
		return name + " has " + bookList.size()+" cars.";
	}
	
	public void printBooks()
	{
		for(Book b : this.bookList)
		{
			b.printBooks();
		}
	}

	public void printClient() {
		System.out.println("Imie: " + name + "\tNazwisko: " + surname);
		logger.info("print client" );
	}

	public void printBook() {
		for (Book b : bookList) {
			b.printBooks();
		}
		logger.info("print books" );

	}

	public void addBook(Book b) {
		bookList.add(b);
		logger.info("added new book \"" +b.title +"\"" );
	}

	public void deleteBook(Book book) {
		
		bookList.remove(book);
			
			logger.info("removed book \"" + book.getTitle()+"\"" );
		}
		

	public void clearBookList() {
		bookList.clear();
		logger.info("cleared bookList");
	}


	public Book find(String title) {
		
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				return book;
			}
			logger.info("found a book \"" + title+"\"" );
		}
		return null;
	}
	
	public List<Book> findAll(String title) {
		List<Book> results= new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				results.add(book);
			}
			logger.info("found all books" );
		}
		return results;
	}

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
	
}
