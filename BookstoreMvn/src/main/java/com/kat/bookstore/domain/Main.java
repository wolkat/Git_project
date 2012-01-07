package com.kat.bookstore.domain;

import com.kat.bookstore.events.*;
import com.kat.bookstore.service.*;

//import org.apache.log4j.PropertyConfigurator;
//import org.apache.log4j.Logger;

public class Main {
	
	//private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws PriceBelowZeroException {
		
		//PropertyConfigurator.configure("src/main/resources/Log4J.properties");

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
		
		c1.deleteBook(c1.findBookTitleAuthor("The Lunatic Cafe", "Laurell K. Hamilton"));
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
		c1.printAll();
		System.out.println("===========================");
		c2.printAll();
		System.out.println("===========================");
		
		EventManager eventManager = new EventManager();
		IBookProcesses cleanBookShelf = new CleanBookShelf();
		IBookProcesses changeShelf = new ChangeShelf();
		
		System.out.println("===========================");
		System.out.println("## Test Events ##");
		c1.findBooksGenre(BookGenre.Fantasy).get(0).setCleanShelf(true);
		
		System.out.println("Toy Story Fantasy CleanShelf /przed/ - " + c1.findBooksTitle("Toy Story").get(0).isCleanShelf());
		System.out.println("Samotny Drama CleanShelf /przed/ - "+ c1.findBooksTitle("Samotny").get(0).isCleanShelf());
		
		eventManager.addProcess(changeShelf);
		eventManager.addProcess(cleanBookShelf);
		eventManager.executeProcesses(c1.findBooksGenre(BookGenre.Fantasy));
		
		System.out.println("Toy Story Fantasy CleanShelf /po/ - " + c1.findBooksTitle("Toy Story").get(0).isCleanShelf());
		System.out.println("Samotny Drama CleanShelf /po/ - "+ c1.findBooksTitle("Samotny").get(0).isCleanShelf());
		
		
		ClientManager clientManager = new ClientManager();
		clientManager.addClient(c1);
		clientManager.addClient(c2);

		for(Client c: clientManager.getAllClients())
		{
			System.out.println(c);
		}
		
		BooksManager booksManager = new BooksManager();
		booksManager.addBook(b1);
		booksManager.addBook(b2);

		for(Client c: clientManager.getAllClients())
		{
			System.out.println(c);
		}
		
	
	}
	
}

