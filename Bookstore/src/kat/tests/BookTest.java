package kat.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kat.project.*;

public class BookTest {

	Client client1 = new Client("Jakub", "Pierzchala");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBook() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy);
		client1.addBook(b);
		assertTrue(client1.getBookList().size()>0);
	}

	@Test
	public void testPrintBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTitle() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);
		assertTrue(client1.getBookList().get(0).getTitle().equals("Polowanie"));
	}

	@Test
	public void testSetTitle() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);
		client1.getBookList().get(0).setTitle("Polowanie na czarownice");
		assertTrue(client1.getBookList().get(0).getTitle().equals("Polowanie na czarownice"));
	}

	@Test
	public void testGetPrice() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);
		assertTrue(client1.getBookList().get(0).getPrice()==19.8);
	}

	@Test
	public void testSetPrice() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);
		client1.getBookList().get(0).setPrice(20);
		assertTrue(client1.getBookList().get(0).getPrice()==20);
	}

	@Test
	public void testIsCleanShelf() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);
		assertTrue(client1.getBookList().get(0).isCleanShelf()==true);
	}

	@Test
	public void testSetCleanShelf() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);
		client1.getBookList().get(0).setCleanShelf(false);
		assertTrue(client1.getBookList().get(0).isCleanShelf()==false);
	}
}
