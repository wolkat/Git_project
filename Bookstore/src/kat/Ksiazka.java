package kat;


	import java.util.ArrayList;
	import java.util.List;

	public class Ksiazka {
		
		public String tytul;
		//public String autor;
		public String ISBN;
		public String cena;
		public String wydawca;
		
		public Ksiazka(String tytul, String ISBN, String cena, String wydawca)
		{
			this.tytul=tytul;
			this.ISBN=ISBN;
			this.cena=cena;
			this.wydawca=wydawca;
		}
		
		public void printBooks()
		{
			System.out.println(tytul + " | Cena: " + cena + "PLN" + " | Wydawca: " + wydawca + " | Nr ISBN: " + ISBN);
		}


	}