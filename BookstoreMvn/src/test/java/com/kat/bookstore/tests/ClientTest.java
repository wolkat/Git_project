package com.kat.bookstore.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.kat.bookstore.domain.*;

public class ClientTest {

	Client client1 = new Client("Jakub", "Pierzchala");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {	
		client1.addBook("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 1988, 19);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClient() throws PriceBelowZeroException {
		Client client2 = new Client("Jerzy","Potok");
		client2.addBook("Zgrzyt","Adam Wrzos", BookGenre.Horror, 2000, 15);
		assertSame(client2.getBookList().size(), 1);
		assertEquals(client2.getName(),("Jerzy"));
		assertEquals(client2.getSurname(),("Potok"));
	}

	@Test
	public void testAddBook() throws PriceBelowZeroException {
		
		assertTrue(client1.getBookList().size() == 1);
	}

	@Test
	public void testGetName() {
		assertEquals(client1.getName(),"Jakub");
	}

	@Test
	public void testSetName() {
		client1.setName("Karol");
		assertEquals(client1.getName(), "Karol");
	}
	
	@Test
	public void testGetSurname() {
		assertEquals(client1.getSurname(),"Potok");
	}

	@Test
	public void testSetSurname() {
		client1.setSurname("Kubek");
		assertEquals(client1.getSurname(), "Kubek");
	}

	@Test
	public void testGetBookList() {
		assertNotNull(client1.getBookList().size());
	}

	@Test
	public void testSetBookList() {
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(new Book("Granica", "Zofia Nalkowska", BookGenre.Drama, 1985, 20));
		client1.setBookList(booklist);
		assertTrue(booklist.size()==1);
	}
	
	@Test
	public void testDeleteBook() throws PriceBelowZeroException {
		client1.deleteBook(client1.findBooksTitle("Polowanie"));
		assertTrue(client1.getBookList().size() == 0);
	}

	@Test
	public void testDeleteBookList() throws PriceBelowZeroException {
		client1.addBook("Zdrada", "Janusz Zieba", BookGenre.Mistery, 1960, 12);
		client1.addBook("Ukojenie", "Maria Wrona", BookGenre.Romance, 1970, 14);
		client1.deleteBookList();
		assertTrue(client1.getBookList().size() == 0);
	}

	@Test
	public void testEditBookPrice() throws PriceBelowZeroException {
		client1.addBook("Pusty dom","Piotr Turek", BookGenre.Horror, 2001, 22);
		client1.editBookPrice(client1.findBooksYear(2001),18);
		assertTrue(client1.getBookList().get(0).getPrice() == 18);
		assertTrue(client1.getBookList().get(1).getPrice() == 18);
		assertSame(29, client1.getBookList().get(0).getPrice());
		assertSame(29, client1.getBookList().get(1).getPrice());
	}

	@Test
	public void testEditBookYear() throws PriceBelowZeroException {
		client1.editBookYear(client1.findBooksTitle("Polowanie"), 1988);
		assertTrue(client1.getBookList().get(0).getYear() == 1988);
	}

	@Test
	public void testEditBookGenre() throws PriceBelowZeroException {
		client1.editBookGenre(client1.findBooksTitle("Polowanie"), BookGenre.Fantasy);
		assertTrue(client1.getBookList().get(0).getGenre() == BookGenre.Fantasy);
	}

	@Test
	public void testFindAllBookByName() throws PriceBelowZeroException {
		client1.addBook("Utrapienie","Marek Krupa", BookGenre.Drama, 2001, 14);
		assertEquals(client1.getBookList().get(0), client1.findBooksTitle("Polowanie").get(0));
		assertSame(client1.getBookList().get(1), client1.findBooksTitle("Utrapienie").get(0));
		assertNotNull(client1.getBookList().get(1));
	}

	@Test
	public void testFindAllBookByType() throws PriceBelowZeroException {
		client1.addBook("Utrapienie","Marek Krupa", BookGenre.Drama, 2001, 14);
		assertEquals(client1.getBookList().get(0), client1.findBooksGenre(BookGenre.Fantasy).get(0));
		assertSame(client1.getBookList().get(1), client1.findBooksGenre(BookGenre.Drama).get(1));
	}

	@Test
	public void testFindAllBookByYear() throws PriceBelowZeroException {
		client1.addBook("Utrapienie","Marek Krupa", BookGenre.Drama, 1988, 14);
		client1.addBook("Katharzis","Andrzej Rolka", BookGenre.Drama, 1988, 10);
		assertEquals(client1.getBookList().get(0), client1.findBooksYear(1988).get(0));
		assertSame(client1.getBookList().get(2), client1.findBooksYear(1988).get(2));
	}
}