package kat;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
	
		List<Ksiazka> book = new ArrayList<Ksiazka>();
		book.add(new Ksiazka("Circus", "1", "49", "Kraina Marzen"));
		book.add(new Ksiazka("Historia wspolczesna", "2", "15,90", "WSiP"));
		book.add(new Ksiazka("Life flowing river", "3", "29", "Wa-wa"));
		book.add(new Ksiazka("Japanese for idiots", "4", "39,99", "Tokyoto"));
		
		
		Klient o = new Klient("Jan", "Nowak", book);
		
		o.printBooks();
		
	}


	
	
}

