package com.kat.bookstore.tests;

import static org.junit.Assert.*;

import org.junit.*;

import com.kat.bookstore.domain.*;

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
		client1.addBook("Polowanie", "Margit Sandemo", BookGenre.Fantasy, 1988, 19);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBook() throws PriceBelowZeroException {
		assertTrue(client1.getBookList().size()>0);
	}

	@Test
	public void testGetTitle() throws PriceBelowZeroException {
		assertSame(client1.getBookList().get(0).getTitle(),"Polowanie");
	}

	@Test
	public void testSetTitle() throws PriceBelowZeroException {
		client1.getBookList().get(0).setTitle("Polowanie na czarownice");
		assertSame(client1.getBookList().get(0).getTitle(),"Polowanie na czarownice");
	}

	@Test
	public void testGetGenre() throws PriceBelowZeroException {
		assertTrue(client1.getBookList().get(0).getGenre().equals(BookGenre.Fantasy));
	}

	@Test
	public void testSetGenre() throws PriceBelowZeroException {
		client1.getBookList().get(0).setGenre(BookGenre.Romance);
		assertTrue(client1.getBookList().get(0).getGenre().equals(BookGenre.Romance));
	}

	@Test
	public void testGetYear() throws PriceBelowZeroException {
		assertTrue(client1.getBookList().get(0).getYear()==1988);
	}

	@Test
	public void testSetReleaseYear() throws PriceBelowZeroException {
		client1.getBookList().get(0).setYear(1990);
		assertTrue(client1.getBookList().get(0).getYear()==1990);
	}

	@Test
	public void testGetPrice() throws PriceBelowZeroException {
		assertTrue(client1.getBookList().get(0).getPrice()==19);
	}

	@Test
	public void testSetPrice() throws PriceBelowZeroException {
		client1.getBookList().get(0).setPrice(9);
		assertTrue(client1.getBookList().get(0).getPrice()==9);
	}

	@Test
	public void testIsCleanShelf() throws PriceBelowZeroException {
		assertTrue(client1.getBookList().get(0).isCleanShelf()==false);
	}

	@Test
	public void testSetCleanShelf() throws PriceBelowZeroException {
		client1.getBookList().get(0).setCleanShelf(true);
		assertTrue(client1.getBookList().get(0).isCleanShelf()==true);
	}
}
