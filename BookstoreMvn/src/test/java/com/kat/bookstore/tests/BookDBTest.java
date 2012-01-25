package com.kat.bookstore.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kat.bookstore.domain.*;
import com.kat.bookstore.services.*;


public class BookDBTest {

	BookDB dbBook = new BookDB();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbBook.addBook(new Book("Pokolenie", "Jerzy Potok", BookGenre.Drama, 20));
	}

	@After
	public void tearDown() throws Exception {
		dbBook.deleteAllBooks();
	}

	@Test
	public void testAddBook() {
		dbBook.addBook(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		dbBook.addBook(new Book("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25));		
		assertEquals(3, dbBook.getAllBooks().size());
	}

	@Test
	public void testGetAllBooks() {
		dbBook.addBook(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		assertEquals(2, dbBook.getAllBooks().size());
	}

	@Test
	public void testDeleteAllBooks() {
		dbBook.addBook(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		dbBook.deleteAllBooks();
		assertEquals(0, dbBook.getAllBooks().size());
		assertTrue(dbBook.getAllBooks().size() == 0);
	}

	@Test
	public void testFindBookByTitle() {
		dbBook.addBook(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		dbBook.addBook(new Book("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25));		
		assertTrue(dbBook.findBookByTitle("Pokolenie").size() == 1);
	}

	@Test
	public void testFindBookByGenre() {
		dbBook.addBook(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		dbBook.addBook(new Book("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25));		
		assertEquals(2, dbBook.findBookByGenre(BookGenre.Drama).size());
	}

	@Test
	public void testDeleteBook() {
		dbBook.addBook(new Book("Ukojenie", "Maria Peszek", BookGenre.Drama, 15));
		dbBook.addBook(new Book("Zgroza", "Andrzej Krupa", BookGenre.Horror, 25));		
		dbBook.deleteBook(dbBook.findBookByTitle("Zgroza"));
		assertEquals(2, dbBook.getAllBooks().size());	
	}

}
