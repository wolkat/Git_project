package kat;


	//import java.util.ArrayList;
	//import java.util.List;

	public class Ksiazka {
		
		//private String genre;
		
		public String title;
		public String price;
	//	public String autor;
	//	public String ISBN;
	//	public String publisher;
		
		public Ksiazka(String tytul, String cena) //, String autor, String ISBN, String wydawca)
		{
			this.title= tytul;
			this.price= cena;
		//	this.autor= autor;
		//	this.ISBN= ISBN;
		//	this.publisher= wydawca;
		}
		
		public void printBooks()
		{
			System.out.println(title + " | Cena: " + price + "PLN" );
		}


	}