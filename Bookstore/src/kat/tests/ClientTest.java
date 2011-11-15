package kat.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kat.project.*;

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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClient() throws PriceBelowZeroException {
		Client client2 = new Client("Jerzy", "Noga");
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client2.addBook(b);
		assertTrue(client2.getBookList().size() == 0);
		assertTrue(client2.getName().equals("Jerzy"));
	}

	@Test
	public void testPrintBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBook() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);;
		assertTrue(client1.getBookList().size() == 1);
	}

	@Test
	public void testGetName() {
		assertTrue(client1.getName() == "Jakub");
	}

	@Test
	public void testSetName() {
		client1.setName("Jerzy");
		assertTrue(client1.getName() == "Jerzy");
	}

	@Test
	public void testGetBookList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBookList() {
		fail("Not yet implemented");
	}

	@Test(expected = PriceBelowZeroException.class, timeout = 100)
	public void testPriceException() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, -2.0);
		client1.addBook(b);
	}
}