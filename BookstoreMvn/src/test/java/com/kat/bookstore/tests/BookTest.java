package com.kat.bookstore.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kat.bookstore.domain.BookGenre;
import com.kat.bookstore.domain.Client;
import com.kat.bookstore.domain.PriceBelowZeroException;


public class BookTest {
	
	Client c1 = new Client("Jan Kurek");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		c1.addBook("Pokolenie", "", BookGenre.Drama, 20);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBook() throws PriceBelowZeroException {
		assertTrue(c1.getBooklist().size()>0);
	}

	@Test
	public void testGetTitle() throws PriceBelowZeroException {
		assertTrue(c1.getBooklist().get(0).getTitle().equals("Pokolenie"));
	}

	@Test
	public void testSetTitle() throws PriceBelowZeroException {
		c1.getBooklist().get(0).setTitle("Ukojenie");
		assertTrue(c1.getBooklist().get(0).getTitle().equals("Ukojenie"));
	}

	@Test
	public void testGetGenre() throws PriceBelowZeroException {
		assertTrue(c1.getBooklist().get(0).getGenre().equals(BookGenre.Drama));
	}

	@Test
	public void testSetGenre() throws PriceBelowZeroException {
		c1.getBooklist().get(0).setGenre(BookGenre.Romance);
		assertTrue(c1.getBooklist().get(0).getGenre().equals(BookGenre.Romance));
	}

	@Test
	public void testGetPrice() throws PriceBelowZeroException {
		assertTrue(c1.getBooklist().get(0).getPrice()==20);
	}

	@Test
	public void testSetPrice() throws PriceBelowZeroException {
		c1.getBooklist().get(0).setPrice(10);
		assertTrue(c1.getBooklist().get(0).getPrice()==10);
	}

	@Test
	public void testIsOnShelf() throws PriceBelowZeroException {
		assertTrue(c1.getBooklist().get(0).isOnShelf()==true);
	}

	@Test
	public void testSetOnShelf() throws PriceBelowZeroException {
		c1.getBooklist().get(0).setOnShelf(false);
		assertTrue(c1.getBooklist().get(0).isOnShelf()==false);
	}

	@Test
	public void testOrdered() throws PriceBelowZeroException {
		assertTrue(c1.getBooklist().get(0).isOrdered()==false);
	}

	@Test
	public void testSetOrdered() throws PriceBelowZeroException {
		c1.getBooklist().get(0).setOnShelf(true);
		assertTrue(c1.getBooklist().get(0).isOnShelf()==true);
	}

}
