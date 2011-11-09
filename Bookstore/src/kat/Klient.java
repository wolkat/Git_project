package kat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class Klient {
	
	private static Logger logger= Logger.getLogger(Klient.class);
	

	public String name;
	public String surname;
	List<Book> listaKsiazek = new ArrayList<Book>();
	 Klient(String imie, String nazwisko)
	{
		this.name = imie;
		this.surname = nazwisko;
	}
	
	public Klient(String imie, String nazwisko, List<Book> book)
	{
		this.name=imie;
		this.surname=nazwisko;
		this.book=book;
	}
	
	public List<Book> book = new ArrayList<Book>();
	
	public void printBooks()
	{
		for(Book b : this.book)
		{
			b.printBooks();
		}
	}

	public void printClient() {
		System.out.println("Imie: " + name + "\tNazwisko: " + surname);
		logger.info("print client" );
	}

	public void printBook() {
		for (Book b : listaKsiazek) {
			b.printBooks();
		}
		logger.info("print books" );

	}

	public void addBook(Book b) {
		listaKsiazek.add(b);
		logger.info("added new book \"" +b.tytul+"\"" );
	}

	public void removeBook(Book book) {
		
		listaKsiazek.remove(book);
			
			logger.info("removed book \"" + book.getTitle()+"\"" );
		}
		

	public void removeBooks() {
		listaKsiazek.clear();
		logger.info("removed all books");
	}

	public void editBook(String tytul, double nowaCena) {
		int pozycja = 0;
		for (Book book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				listaKsiazek.set(pozycja, new Book(tytul, nowaCena));
				break;
			}
			pozycja++;
			logger.info("edited a book \"" + tytul+"\"" );
		}
	}

	public Book find(String tytul) {
		
		for (Book book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				return book;
			}
			logger.info("found a book \"" + tytul+"\"" );
		}
		return null;
	}
	
	public List<Book> findAll(String tytul) {
		List<Book> results= new ArrayList<Book>();
		for (Book book : listaKsiazek) {
			if (book.getTitle().equals(tytul)) {
				results.add(book);
			}
			logger.info("found all books" );
		}
		return results;
	}

	public String getName() {
		return name;
	}

	public void setName(String imie) {
		this.name = imie;
		logger.info("set client's name" );
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String nazwisko) {
		this.surname = nazwisko;
		logger.info("set client's surname" );
	}

	public List<Book> getBookList() {
		return listaKsiazek;
	}

	public void setBookList(List<Book> bookList) {
		this.listaKsiazek = bookList;
		logger.info("set booklist" );
	}
	
}
