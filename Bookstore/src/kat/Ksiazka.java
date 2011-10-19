package kat;


	import java.util.ArrayList;
	import java.util.List;

	public class Ksiazka {
		
		private String genre;
		
		public String title;
		public String autor;
		public String ISBN;
		public String price;
		public String publisher;
		
		public Ksiazka(String tytul, String autor, String ISBN, String cena, String wydawca)
		{
			this.title= tytul;
			this.autor= autor;
			this.ISBN= ISBN;
			this.price= cena;
			this.publisher= wydawca;
		}
		
		public void printBooks()
		{
			System.out.println(title + " | Cena: " + price + "PLN" + " | Wydawca: " + publisher + " | Nr ISBN: " + ISBN);
		}


	}