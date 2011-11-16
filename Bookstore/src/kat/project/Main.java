package kat.project;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Main {
	
	private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("Log4J.properties");
	
		List<Book> bookList1 = new ArrayList<Book>();
		List<Book> bookList2 = new ArrayList<Book>();
		
		Client c1 = new Client("Jan", "Nowak", bookList1);
		Client c2 = new Client("Julek", "Kowal", bookList2);
		
		Book b1 = new Book("The Lunatic Cafe","Laurell K. Hamilton",BookGenre.Horror, 21.5);
		Book b2 = new Book("Spellbound","Margit Sandemo", 29.0);
		Book b3 = new Book("Achaja","Andrzej Ziemianski", 18.9);
		
		try {
			b1.setPrice(-2.0);
			} catch (PriceBelowZeroException e) {
			logger.error(e);
		}
		
		try {
			b1.setPrice(28.0);
		} catch (PriceBelowZeroException e) {
			e.printStackTrace();
			logger.error("niepoprawna cena"+b1.getTitle());
		}
		
		c1.addBook(b1);
		c1.addBook(b2);
		c2.addBook(b3);
		c1.deleteBook(c1.findBookTitle("The Lunatic Cafe"));
		
		System.out.print("Lista ksiazek klienta ");
		c1.printClient();
		System.out.println("===========================");
		c1.printBooks();
				
		System.out.println("===========================");
		
		System.out.print("Lista ksiazek klienta ");
		c2.printClient();
		System.out.println("===========================");
		c2.printBooks();
		
		
	}
	
}

