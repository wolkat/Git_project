package kat;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
	
		List<Ksiazka> book = new ArrayList<Ksiazka>();
		//book.add(new Ksiazka("Circus","", "1", "49", "Kraina Marzen"));
		
		
		
		Klient o = new Klient("Jan", "Nowak", book);
		
		o.printBooks();
		
	}


	
	
}

