package kat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class Klient {
	
	private static Logger logger= Logger.getLogger(Klient.class);
	

	public String imie;
	public String nazwisko;
	List<Ksiazka> listaKsiazek = new ArrayList<Ksiazka>();
	
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
		for(Ksiazka b : this.book)
		{
			b.printBooks();
		}
	}

	public void printClient() {
		System.out.println("Imie: " + imie + "\tNazwisko: " + nazwisko);
		logger.info("print client" );
	}

	public void printBook() {
		for (Ksiazka b : listaKsiazek) {
			b.printBooks();
		}
		logger.info("print books" );

	}

	public void addBook(Ksiazka b) {
		listaKsiazek.add(b);
		logger.info("added new book \"" +b.tytul+"\"" );
	}

	public void removeBook(String tytul) {
		int pozycja = 0;
		for (Ksiazka book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				listaKsiazek.remove(pozycja);
				break;
			}
			pozycja++;
			logger.info("removed book \"" + tytul+"\"" );
		}

	}

	public void removeBooks() {
		listaKsiazek.clear();
		logger.info("removed all books");
	}

	public void editBook(String tytul, double nowaCena) {
		int pozycja = 0;
		for (Ksiazka book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				listaKsiazek.set(pozycja, new Ksiazka(tytul, nowaCena));
				break;
			}
			pozycja++;
			logger.info("edited a book \"" + tytul+"\"" );
		}
	}

	public void find(String tytul) {
		int pozycja = 0;
		for (Ksiazka book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				System.out.println("Szukana gra " + book.getTitle()
						+ " znajduje się na pozycji " + pozycja);
			}
			pozycja++;
			logger.info("found a book \"" + tytul+"\"" );
		}
	}

	public String getName() {
		return imie;
	}

	public void setName(String imie) {
		this.imie = imie;
		logger.info("set client's name" );
	}

	public String getSurname() {
		return nazwisko;
	}

	public void setSurname(String nazwisko) {
		this.nazwisko = nazwisko;
		logger.info("set client's surname" );
	}

	public List<Ksiazka> getBookList() {
		return listaKsiazek;
	}

	public void setBookList(List<Ksiazka> bookList) {
		this.listaKsiazek = bookList;
		logger.info("set booklist" );
	}
	
}
