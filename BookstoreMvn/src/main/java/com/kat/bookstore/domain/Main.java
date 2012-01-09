package com.kat.bookstore.domain;

import org.apache.log4j.*;

import com.kat.bookstore.events.*;
import com.kat.bookstore.service.*;


public class Main {	
	
	private static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("Log4J.properties");
		System.out.print("Main works");
		logger.info("Started main class");
		
		
		Client c1 = new Client("Jan", "Nowak");
		Client c2 = new Client("Julek", "Kowal");
		
		Book b1 = new Book("The Lunatic Cafe","Laurell K. Hamilton",BookGenre.Horror, 2007, 21.5);
		Book b2 = new Book("Spellbound","Margit Sandemo", BookGenre.Romance, 1988, 29.0);
		Book b3 = new Book("Achaja","Andrzej Ziemianski", BookGenre.Fantasy, 2008, 18.9);
		Book b4 = new Book("Toy Story","Andrzej Ziemianski",BookGenre.Horror, 2007, 21.5);
		Book b5 = new Book("Samotny","Margit Sandemo", BookGenre.Drama, 1990, 18.9);
		Book b6 = new Book("Equal Rites","Terry Pratchett", BookGenre.Fantasy, 1999, 19.0);
		Book b7 = new Book("Trzy siostry","Terry Pratchett", BookGenre.Mistery, 1998, 17.0);
			
		b2.setGenre(BookGenre.Fantasy);
		b3.setGenre(BookGenre.Fantasy);
		c1.addBook(b1);
		c1.addBook(b2);
		c1.addBook(b4);
		c1.addBook(b5);
		c2.addBook(b3);
		c2.addBook(b6);
		c2.addBook(b7);
		
		c1.deleteBooks(c1.findBooksTitle("The Lunatic Cafe"));
		c2.deleteBooks(c2.findBooksAuthor("Terry Pratchett"));
		
		
		System.out.print("Lista ksiazek klienta ");
		c1.printClient();
		System.out.println("===========================");
		c1.printBooks();
				
		System.out.println("===========================");
		
		System.out.print("Lista ksiazek klienta ");
		c2.printClient();
		System.out.println("===========================");
		c2.printBooks();
		System.out.println("===========================");
		
		EventMgr evtMgr = new EventMgr();
		IBookProcesses cleanBookShelf = new CleanBookShelf();
		IBookProcesses changeShelf = new ChangeShelf();
		
		System.out.println("===========================");
		System.out.println("## Test Events ##");
		c1.findBooksGenre(BookGenre.Fantasy).get(0).setCleanShelf(true);
		
		System.out.println("Toy Story Fantasy CleanShelf /przed/ - " + c1.findBooksTitle("Toy Story").get(0).isCleanShelf());
		System.out.println("Samotny Drama CleanShelf /przed/ - "+ c1.findBooksTitle("Samotny").get(0).isCleanShelf());
		
		evtMgr.addProcess(changeShelf);
		evtMgr.addProcess(cleanBookShelf);
		evtMgr.executeProcesses(c1.findBooksGenre(BookGenre.Fantasy));
		
		System.out.println("Toy Story Fantasy CleanShelf /po/ - " + c1.findBooksTitle("Toy Story").get(0).isCleanShelf());
		System.out.println("Samotny Drama CleanShelf /po/ - "+ c1.findBooksTitle("Samotny").get(0).isCleanShelf());
		
		
		ClientBaseMgr clientMgr = new ClientBaseMgr();
		clientMgr.addClient(c1);
		clientMgr.addClient(c2);

		for(Client c: clientMgr.getAllClients())
		{
			System.out.println(c);
		}
		
		BooksBaseMgr booksMgr = new BooksBaseMgr();
		booksMgr.addBook(b1);
		booksMgr.addBook(b2);

		for(Client c: clientMgr.getAllClients())
		{
			System.out.println(c);
		}
		
	
	}
	
}

