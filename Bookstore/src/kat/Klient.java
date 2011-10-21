package kat;

import java.util.ArrayList;
import java.util.List;

public class Klient {

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
	}

	public void printBook() {
		for (Ksiazka b : listaKsiazek) {
			b.printBooks();
		}

	}

	public void addBook(Ksiazka b) {
		listaKsiazek.add(b);
	}

	public void removeBook(String tytul) {
		int pozycja = 0;
		for (Ksiazka book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				listaKsiazek.remove(pozycja);
				break;
			}
			pozycja++;
		}

	}

	public void removeBooks() {
		listaKsiazek.clear();
	}

	public void editBook(String tytul, int nowaCena) {
		int pozycja = 0;
		for (Ksiazka book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				listaKsiazek.set(pozycja, new Ksiazka(tytul, nowaCena));
				break;
			}
			pozycja++;
		}
	}

	public void szukaj(String tytul) {
		int pozycja = 0;
		for (Ksiazka book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				System.out.println("Szukana gra " + book.getTitle()
						+ " znajduje się na pozycji " + pozycja);
			}
			pozycja++;
		}
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public List<Ksiazka> getBookList() {
		return listaKsiazek;
	}

	public void setBookList(List<Ksiazka> bookList) {
		this.listaKsiazek = bookList;
	}
	
}
