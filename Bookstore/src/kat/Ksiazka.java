package kat;


	//import java.util.ArrayList;
	//import java.util.List;

	public class Ksiazka {
		
	//	private String genre;
		
		public String tytul;
		public Integer cena;
	//	public String autor;
	//	public String ISBN;
	//	public String publisher;
		
		public Ksiazka(String tytul, Integer cena) //, String autor, String ISBN, String wydawca)
		{
			this.tytul= tytul;
			this.cena= cena;
		//	this.autor= autor;
		//	this.ISBN= ISBN;
		//	this.publisher= wydawca;
		}
		
		public void printBooks()
		{
			System.out.println(tytul + "\tCena: " + cena + "PLN" );
		}


		public String getTitle() {
			return tytul;
		}

		public void setTitle(String tytul) {
			this.tytul = tytul;
		}

		public Integer getPrice() {
			return cena;
		}

		public void setPrice(Integer cena) {
			this.cena = cena;
		}
	}