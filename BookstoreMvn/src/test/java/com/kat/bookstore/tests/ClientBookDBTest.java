package com.kat.bookstore.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kat.bookstore.domain.*;
import com.kat.bookstore.services.*;


public class ClientBookDBTest {
	
	ClientDB dbClient = new ClientDB();
	BookDB dbBook = new BookDB();
	ClientBookDB dbClientBook = new ClientBookDB();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbClient.addClient(new Client("Jan Kurek"));
		dbClient.addClient(new Client("Piotr Glinko"));
		dbBook.addBook(new Book("Pokolenie", "Jerzy Potok", BookGenre.Drama, 20));
		dbBook.addBook(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		dbClientBook.addBookToClient(dbClient.findClientByName("Jan Kurek"), dbBook.findBookByTitle("Pokolenie"));
	}

	@After
	public void tearDown() throws Exception {
		dbClientBook.deleteAllClientBook();
		dbClient.deleteAllClient();
		dbBook.deleteAllBooks();
	}
	
	@Test
	public void testAddBookToClient() {
		dbBook.addBook(new Book("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25));		
		dbClientBook.addBookToClient(dbClient.findClientByName("Jan Kurek"), dbBook.findBookByGenre(BookGenre.Horror));
		assertEquals(2, dbClientBook.getClientBook(dbClient.findClientByName("Jan Kurek")).size());
	}

	@Test
	public void testDeleteAllBookFromClient() {
		dbBook.addBook(new Book("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25));		
		dbClientBook.addBookToClient(dbClient.findClientByName("Piotr Glinko"), dbBook.findBookByGenre(BookGenre.Horror));
		assertTrue(dbClientBook.getClientBook(dbClient.findClientByName("Jan Kurek")).size() == 1);
		assertTrue(dbClientBook.getClientBook(dbClient.findClientByName("Piotr Glinko")).size() == 1);
		dbClientBook.deleteAllBooksFromClient(dbClient.findClientByName("Piotr Glinko"));
		assertTrue(dbClientBook.getClientBook(dbClient.findClientByName("Jan Kurek")).size() == 1);
		assertTrue(dbClientBook.getClientBook(dbClient.findClientByName("Piotr Glinko")).size() == 0);
	}

	@Test
	public void testDeleteAllClientBook() {
		dbBook.addBook(new Book("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25));		
		dbClientBook.addBookToClient(dbClient.findClientByName("Jan Kurek"), dbBook.findBookByGenre(BookGenre.Horror));
		assertNotNull(dbClientBook.getClientBook(dbClient.findClientByName("Jan Kurek")));
		dbClientBook.deleteAllClientBook();
		assertTrue(dbClientBook.getClientBook(dbClient.findClientByName("Jan Kurek")).size() == 0);
	}

	@Test
	public void testGetClientBook() {
		assertEquals(1, dbClientBook.getClientBook(dbClient.findClientByName("Jan Kurek")).size());
	}

}
