package com.kat.bookstore.domain;

import org.apache.log4j.*;

import com.kat.bookstore.events.*;
import com.kat.bookstore.services.Condition;
import com.kat.bookstore.services.BookDB;
import com.kat.bookstore.services.ClientDB;
import com.kat.bookstore.services.ClientBookDB;



public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws PriceBelowZeroException {

		PropertyConfigurator.configure("src/resources/java/Log4J.properties");

		Client c1 = new Client("Jan Kurek");
		Client c2 = new Client("Piotr Glinko");
		try {
			c1.addBook("Pokolenie", "Jerzy Potok", BookGenre.Drama, 20);
			c1.addBook("Ukojenie", "Maria Peszek", BookGenre.Drama, 15);
			c1.addBook("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25);		
			c2.addBook("Zauroczenie","Margit Sandemo", BookGenre.Romance, 18);
			c2.addBook("Samotny","Margit Sandemo", BookGenre.Fantasy, -2);
			c2.addBook("Achaja","Anrzej Ziemianski", BookGenre.Fantasy, 29);
		} catch (PriceBelowZeroException exception) {
			logger.error(exception);
		}

		c1.printAll();

		c1.editBookPrice(c1.findAllBooksByGenre(BookGenre.Fantasy), 1);
		c1.editBookGenre(c1.findAllBooksByTitle("Zauroczenie"), BookGenre.Romance);
		try {
			c1.editBookPrice(c1.findAllBooksByTitle("Ukojenie"), -5);
		} catch (PriceBelowZeroException exception) {
			logger.error(exception);
		}

		c2.printAll();

		Manager mgr = new Manager();

		IBookProcesses emptyShelf = new EmptyShelf();
		IBookProcesses order = new OrderBook();

		System.out.println("== Book Events ==");
		c1.findAllBooksByTitle("Pokolenie").get(0).setOnShelf(false);
		System.out.println("Pokolenie empty shelf before - "
				+ c1.findAllBooksByTitle("Pokolenie").get(0).isOnShelf());
		System.out.println("Pokolenie order book before - "
				+ c1.findAllBooksByTitle("Pokolenie").get(0).isOrdered());
		mgr.addProcess(emptyShelf);
		mgr.addProcess(order);
		mgr.executeProcesses(c1.findAllBooksByGenre(BookGenre.Drama));
		System.out.println("Pokolenie empty shelf after - "
				+ c1.findAllBooksByTitle("Pokolenie").get(0).isOnShelf());
		System.out.println("Pokolenie order book after - "
				+ c1.findAllBooksByTitle("Pokolenie").get(0).isOrdered());
		
		System.out.println("\n\n=============================");
		
		ClientDB dbClient = new ClientDB();

		dbClient.addClient(c1);
		dbClient.addClient(c2);
		for (Client Client : dbClient.getAllClients())
		{
			System.out.println(Client.getName());
		}
		
		System.out.println("==========================");
		
		BookDB dbBook = new BookDB();

		dbBook.addBook(c1.getBooklist().get(0));
		dbBook.addBook(c1.getBooklist().get(1));
		dbBook.addBook(c1.getBooklist().get(2));
		for (Book b : dbBook.getAllBooks())
		{
			System.out.println("Title: " + b.getTitle() + "\tAuthor: "+ b.getAuthor() +"\tGenre: " + b.getGenre() + "\tPrice: " + b.getPrice());
		}
		
		ClientBookDB dbClientBook = new ClientBookDB();

		dbClientBook.addBookToClient(dbClient.findClientByName("Jan Kurek"), dbBook.findBookByTitle("Pokolenie"));
		dbClientBook.addBookToClient(dbClient.findClientByName("Piotr Glinko"), dbBook.findBookByGenre(BookGenre.Fantasy));

		System.out.println("\n============================");
		c1.printClient();
		for (Book b : dbClientBook.getClientBook(dbClient.findClientByName("Jan Kurek")))
		{
			System.out.println("Title: " + b.getTitle() + "\tAuthor: " + b.getAuthor() + 
					"\tGenre: " + b.getGenre() + "\tPrice: " + b.getPrice());
		}
		
		System.out.println("\n==============================");
		c2.printClient();
		
		for (Book b : dbClientBook.getClientBook(dbClient.findClientByName("Piotr Glinko")))
		{
			System.out.println("Title: " + b.getTitle() + "\tAuthor: " + b.getAuthor() + 
					"\tGenre: " + b.getGenre() + "\tPrice: " + b.getPrice());
		}
		
		
		System.out.println("\n==========================");
		System.out.println("Books: price > 20");
		dbBook.printBookCondition(dbBook.getAllBooks(), new Condition() 
			{
				@Override
				public boolean getCondition(Book b) {
						if (b.getPrice() > 20)
							return true;
					return false;
				}
			}
		);
		
		
		dbClientBook.deleteAllClientBook();
		dbBook.deleteAllBooks();
		dbClient.deleteAllClient();
	}

}