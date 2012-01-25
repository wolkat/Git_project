package com.kat.bookstore.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kat.bookstore.domain.Book;
import com.kat.bookstore.domain.BookGenre;
import com.kat.bookstore.domain.Client;
import com.kat.bookstore.domain.PriceBelowZeroException;


public class ClientTest {

	Client c1 = new Client("Jan Kurek");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		c1.addBook("Pokolenie", "Jerzy Potok", BookGenre.Drama, 20);
	}

	@After
	public void tearDown() throws Exception {
		c1.deleteAllBooks();
	}

	@Test
	public void testClient() throws PriceBelowZeroException {
		Client c2 = new Client("Piotr Glinko");
		c2.addBook("Ukojenie", "Maria Peszek", BookGenre.Drama, 15);
		assertTrue(c2.getBooklist().size() == 1);
		assertTrue(c2.getName().equals("Piotr Glinko"));
	}

	@Test
	public void testAddBook() throws PriceBelowZeroException {
		assertTrue(c1.getBooklist().size() == 1);
	}

	@Test
	public void testDeleteBook() throws PriceBelowZeroException {
		c1.deleteBook(c1.findAllBooksByTitle("Pokolenie"));
		assertTrue(c1.getBooklist().size() == 0);
	}

	@Test
	public void testDeleteAllBooks() throws PriceBelowZeroException {
		c1.addBook("Ukojenie", "Maria Peszek", BookGenre.Drama, 15);
		c1.addBook("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25);
		c1.deleteAllBooks();
		assertTrue(c1.getBooklist().size() == 0);
	}

	@Test
	public void testEditBookPrice() throws PriceBelowZeroException {
		c1.addBook("Ukojenie", "Maria Peszek", BookGenre.Drama, 15);
		c1.editBookPrice(c1.findAllBooksByGenre(BookGenre.Drama),12);
		assertTrue(c1.getBooklist().get(0).getPrice() == 12);
		assertTrue(c1.getBooklist().get(1).getPrice() == 12);
		assertSame(12, c1.getBooklist().get(0).getPrice());
		assertSame(12, c1.getBooklist().get(1).getPrice());
	}

	@Test
	public void testEditBookGenre() throws PriceBelowZeroException {
		c1.editBookGenre(c1.findAllBooksByTitle("Pokolenie"), BookGenre.Mistery);
		assertTrue(c1.getBooklist().get(0).getGenre() == BookGenre.Mistery);
	}

	@Test
	public void testFindAllBookByTitle() throws PriceBelowZeroException {
		c1.addBook("Ukojenie", "Maria Peszek", BookGenre.Drama, 15);
		assertEquals(c1.getBooklist().get(0), c1.findAllBooksByTitle("Pokolenie").get(0));
		assertSame(c1.getBooklist().get(1), c1.findAllBooksByTitle("Ukojenie").get(0));
		assertNotNull(c1.getBooklist().get(1));
	}

	@Test
	public void testFindAllBooksByGenre() throws PriceBelowZeroException {
		c1.addBook("Ukojenie", "Maria Peszek", BookGenre.Drama, 15);
		assertEquals(c1.getBooklist().get(0), c1.findAllBooksByGenre(BookGenre.Drama).get(0));
		assertSame(c1.getBooklist().get(1), c1.findAllBooksByGenre(BookGenre.Drama).get(1));
	}
	
	@Test
	public void testGetName() {
		assertTrue(c1.getName() == "Jan Kurek");
	}

	@Test
	public void testSetName() {
		c1.setName("Piotr Glinko");
		assertTrue(c1.getName() == "Piotr Glinko");
	}

	@Test
	public void testGetBooklist() throws PriceBelowZeroException {
		assertNotNull(c1.getBooklist());
		
	}
	
	@Test
	public void testSetBooklist() {
		List<Book> list = new ArrayList<Book>();
		list.add(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		c1.setBooklist(list);
		assertTrue(list.size()==1);
	}

	@Test(expected = PriceBelowZeroException.class, timeout = 100)
	public void testPriceBelowZeroException() throws PriceBelowZeroException {
		c1.addBook("Ukojenie", "Maria Peszek", BookGenre.Drama, -15);
	}

}
