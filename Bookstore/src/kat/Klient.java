package kat;

import java.util.ArrayList;
import java.util.List;

public class Klient {

	public String imie;
	public String nazwisko;
	
	public Klient(String imie, String nazwisko)
	{
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	
	public Klient(String imie, String nazwisko, List<Ksiazka> book)
	{
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.book=book;
	}
	
	public List<Ksiazka> book = new ArrayList<Ksiazka>();
	
	public void printBooks()
	{
		for(Ksiazka c : this.book)
		{
			c.printBooks();
		}
	}

}
