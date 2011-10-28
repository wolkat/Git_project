package kat;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Main {
	
	private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure("Log4J.properties");
	
		List<Ksiazka> listaKsiazek = new ArrayList<Ksiazka>();
		listaKsiazek.add(new Ksiazka("Circus",49.0));
		Ksiazka b = new Ksiazka("Place");
		//book.add(b);
		
		
		try {
			b.setPrize(-2.0);
		} catch (MyException e) {

			logger.error(e);
			logger.fatal(e);
			logger.info(e);
			logger.warn(e);
		}
		
		Klient c = new Klient("Jan", "Nowak", listaKsiazek);
		c.addBook(b);
		c.removeBook("Circus");
		c.addBook(b);
		c.editBook("Circus", 29.0);
		c.getName();
		c.find("Place");
		c.printBooks();
		c.printClient();
		
	}


	
	
}

