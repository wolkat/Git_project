package kat;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Main {
	
	private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("Log4J.properties");
	
		List<Book> listaKsiazek = new ArrayList<Book>();
		listaKsiazek.add(new Book("Circus",49.0));
		Book b = new Book("Place");
		//book.add(b);
		
		
		try {
			b.setPrice(-2.0);
		} catch (PriceBelowZeroException e) {

			logger.error(e);
			logger.fatal(e);
			logger.info(e);
			logger.warn(e);
		}
		
		Client c = new Client("Jan", "Nowak", listaKsiazek);
		c.addBook(b);
		//c.removeBook("Circus");
		c.addBook(b);
		c.editBook("Circus", 29.0);
		c.getName();
		c.find("Place");
		c.printBooks();
		c.printClient();
		
	}


	
	
}

