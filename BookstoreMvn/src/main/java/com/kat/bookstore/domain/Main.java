package com.kat.bookstore.domain;

import org.apache.log4j.*;


import com.kat.bookstore.events.*;
import com.kat.bookstore.service.*;


public class Main {	
	
	private static Logger logger = Logger.getLogger(Main.class);
	
	public static void cleanDB(BooksDB booksMgr,
			ClientDB clientMgr, ClientBookDB dbClientBook) {
				dbClientBook.deleteAll();
				booksMgr.deleteAllBooks();
				clientMgr.deleteAllClients();
	};
	
	public static void main(String[] args) throws PriceBelowZeroException {
		
		logger.info("Started main class");	
		
		Client c1 = new Client("Jan", "Nowak");
		Client c2 = new Client("Julek", "Kowal");
		
		try {
		c1.addBook("The Lunatic Cafe","Laurell K. Hamilton",BookGenre.Horror, 2007, 21);
		c1.addBook("Spellbound","Margit Sandemo", BookGenre.Romance, 1988, 29);
		c1.addBook("Achaja","Andrzej Ziemianski", BookGenre.Fantasy, 2008, 18);
		c2.addBook("Toy Story","Andrzej Ziemianski",BookGenre.Horror, 2007, 20);
		c2.addBook("Samotny","Margit Sandemo", BookGenre.Drama, 1990, 18);
		c2.addBook("Equal Rites","Terry Pratchett", BookGenre.Fantasy, 1999, 19);
		c2.addBook("Trzy siostry","Terry Pratchett", BookGenre.Mistery, 1998, 17);	
		} catch (PriceBelowZeroException exception) {
			logger.error(exception);
		}
	
	
		System.out.print("Lista ksiazek klienta ");
		c1.printClient();
		c1.printBooks();
				
		System.out.println("===========================");
		
		System.out.print("Lista ksiazek klienta ");
		c2.printClient();
		c2.printBooks();
		System.out.println("===========================");
		
		c1.editBookPrice(c1.findBooksAuthor("Margit Sandemo"), 10);
		try {
			c1.editBookPrice(c1.findBooksTitle("Achaja"), -1);
		} catch (PriceBelowZeroException exception) {
			logger.error(exception);
		}
		
		c2.editBookYear(c2.findBooksTitle("Samotny"), 1989);
		c2.editBookGenre(c2.findBooksAuthor("Terry Pratchett"), BookGenre.Fantasy);
		
		System.out.println("=======Po zmianach=========");
		System.out.print("Lista ksiazek klienta ");
		c1.printClient();
		c1.printBooks();
				
		System.out.println("===========================");
		
		System.out.print("Lista ksiazek klienta ");
		c2.printClient();
		c2.printBooks();
		System.out.println("===========================");

//================================			
		EventMgr evtMgr = new EventMgr();
		IBookProcesses cleanBookShelf = new CleanBookShelf();
		//IBookProcesses changeShelf = new ChangeShelf();
	
		System.out.println("===========================");
		System.out.println("## Test Events ##");
		c1.findBooksGenre(BookGenre.Fantasy).get(0).setCleanShelf(true);
		
		//System.out.println("Toy Story Fantasy CleanShelf /przed/ - " + c1.findBooksTitle("Toy Story").get(0).isCleanShelf());
		//System.out.println("Samotny Drama CleanShelf /przed/ - "+ c1.findBooksTitle("Samotny").get(0).isCleanShelf());
		
		
		evtMgr.addProcess(cleanBookShelf);
		evtMgr.executeProcesses(c1.findBooksGenre(BookGenre.Fantasy));
		
		//System.out.println("Toy Story Fantasy CleanShelf /po/ - " + c1.findBooksTitle("Toy Story").get(0).isCleanShelf());
		//System.out.println("Samotny Drama CleanShelf /po/ - "+ c1.findBooksTitle("Samotny").get(0).isCleanShelf());
		
		
		ClientDB dbClient = new ClientDB();
		dbClient.addClient(c1);
		dbClient.addClient(c2);

		for(Client c: dbClient.getAllClients())
		{
			System.out.println(c);
		}
		
		BooksDB dbBook = new BooksDB();
		dbBook.addBook(c1.getBookList().get(0));
		dbBook.addBook(c1.getBookList().get(1));
		dbBook.addBook(c1.getBookList().get(2));
		dbBook.addBook(c2.getBookList().get(0));
		dbBook.addBook(c2.getBookList().get(1));
		dbBook.addBook(c2.getBookList().get(2));
		dbBook.addBook(c2.getBookList().get(3));

		for(Book b: dbBook.getAllBooks())
		{
			System.out.println(b);
		}
		
		ClientBookDB dbClientBook = new ClientBookDB();
		dbClientBook.addBooksToClient(dbClient.findClientBySurname("Nowak"), dbBook.findBookByTitle("The Lunatic Cafe"));
		dbClientBook.addBooksToClient(dbClient.findClientBySurname("Kowal"), dbBook.findBookByAuthor("Margit Sandemo"));

		System.out.println("==========================");
		c1.printClient();
		
		
	/*	for (Book b : dbClientBook.getClientBooks(dbClient.findClientBySurname("Nowak")))
		{
			System.out.println("Title: " + b.getTitle() +"\tAuthor: "+ b.getAuthor() + "\tGenre: " + b.getGenre() + "\tYear: " + b.getYear() + "\tPrice: " + b.getPrice());
		}
		
		System.out.println("=============================");
		c2.printClient();
		
		for (Book b : dbClientBook.getClientBooks(dbClient.findClientBySurname("Kowal")))
		{
			System.out.println("Title: " + b.getTitle() +"\tAuthor: "+ b.getAuthor() + "\tGenre: " + b.getGenre() + "\tYear: " + b.getYear() + "\tPrice: " + b.getPrice());
		}*/
		
		System.out.println("Warunek: tylko ksiazki z cenami > 20 zl");
		dbBook.printBookWithCondition(dbBook.getAllBooks(), new Condition()
			{
				@Override
				public boolean getCondition(Book b) {
						if (b.getPrice() > 35)
							return true;
					return false;
				}

			}
		);
		
		System.out.println("Wszystkie gry powyzej 2008 roku");
		dbBook.printBookWithCondition(dbBook.getAllBooks(), new Condition() 
			{
				@Override
				public boolean getCondition(Book b) {
						if (b.getYear() > 2000)
							return true;
					return false;
				}

			}
		);
	
	}
	
}

