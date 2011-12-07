package kat.domain;

import java.util.ArrayList;
import java.util.List;

import kat.events.*;
import kat.services.ClientManager;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Main {
	
	private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws PriceBelowZeroException {
		
		PropertyConfigurator.configure("Log4J.properties");
		
		List<Client> clientList = new ArrayList<Client>();
	
		List<Book> bookList1 = new ArrayList<Book>();
		List<Book> bookList2 = new ArrayList<Book>();
		
		Client c1 = new Client("Jan", "Nowak", bookList1);
		Client c2 = new Client("Julek", "Kowal", bookList2);
		
		
		Book b1 = new Book("The Lunatic Cafe","Laurell K. Hamilton",BookGenre.Horror, 21.5);
		Book b2 = new Book("Spellbound","Margit Sandemo", 29.0);
		Book b3 = new Book("Achaja","Andrzej Ziemianski", 18.9);
		Book b4 = new Book("Toy Story","Andrzej Ziemianski",BookGenre.Fantasy, 21.5);
		Book b5 = new Book("Samotny","Margit Sandemo", BookGenre.Drama, 18.9);
		Book b6 = new Book("Equal Rites","Terry Pratchett", BookGenre.Fantasy, 19.0);
		Book b7 = new Book("Trzy siostry","Terry Pratchett", BookGenre.Fantasy, 17.0);
		
				
		try {
			b1.setPrice(-2.0);
			b2.setPrice(28.0);
		} catch (PriceBelowZeroException e) {
			e.printStackTrace();
			logger.error("niepoprawna cena"+b1.getTitle());
		}
		
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
		
		clientList.add(0, c1);
		clientList.add(1, c2);
		
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
		System.out.println("Ilosc klientow: "+ clientList.size());
		
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
		
	
	}
	
}

