package kat.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kat.domain.*;

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
		
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClient() throws PriceBelowZeroException {
		Client client2 = new Client("Jerzy", "Noga");
		Book b2 = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client2.addBook(b2);
		assertSame(client2.getBookList().size(), 1);
		assertEquals(client2.getName(),("Jerzy"));
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
		assertSame(client1.getBookList().size(), 1);
	}

	@Test
	public void testGetName() {
		assertEquals(client1.getName(),"Jakub");
	}

	@Test
	public void testSetName() {
		client1.setName("Jerzy");
		assertEquals(client1.getName(), "Jerzy");
	}

	@Test
	public void testGetBookList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBookList() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindBookTitleAuthor() {
		assertNotNull(client1.findBookTitleAuthor("Polowanie", "Margit Sandemo"));
		assertSame(client1.findBookTitleAuthor("Polowanie", "Margit Sandemo").getTitle(),"Polowanie" );
		assertSame(client1.findBookTitleAuthor("Polowanie", "Margit Sandemo").getAuthor(),"Margit Sandemo");
	}
	
	@Test
	public void testFindBooksGenre() {
		assertNotNull(client1.findBooksGenre(BookGenre.Fantasy));
		assertTrue(client1.findBooksGenre(BookGenre.Fantasy).size() > 0 );
	}
	
	@Test
	public void testFindBooksAuthor() {
		assertNotNull(client1.findBooksAuthor("Margit Sandemo"));
		assertTrue(client1.findBooksAuthor("Margit Sandemo").size() > 0 );
	}
	
	@Test
	public void testFindBooksTitle() {
		assertNotNull(client1.findBooksTitle("Margit Sandemo"));
		assertTrue(client1.findBooksTitle("Margit Sandemo").size() > 0 );
	}
	
	@Test
	public void testDeleteBook() {
		Book b1 = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 19.8);
		client1.addBook(b1);
		assertEquals(client1.getBookList().size(), 2);
		client1.deleteBook(b1);
		assertEquals(client1.getBookList().size(), 1);
	}
	
	@Test
	public void testDeleteBooklist() {
		Book b3 = new Book("Samotny","Margit Sandemo", BookGenre.Drama, 18.9);
		Book b4 = new Book("Equal Rites","Terry Pratchett", BookGenre.Fantasy, 19.0);
		client1.addBook(b3);
		client1.addBook(b4);
		assertNotNull(client1.getBookList().size());
		client1.deleteBookList();
		assertNull(client1.getBookList().size());
	}
	
	@Test(expected = PriceBelowZeroException.class, timeout = 100)
	public void testPriceBelowZeroException() throws PriceBelowZeroException {
		Book b = new Book("Polowanie", "Margit Sandemo", BookGenre.Fantasy, -2.0);
		client1.addBook(b);
	}
}