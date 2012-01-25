package com.kat.bookstore.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kat.bookstore.domain.Client;
import com.kat.bookstore.services.ClientDB;


public class ClientDBTest {
	
	ClientDB dbClient = new ClientDB();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbClient.addClient(new Client("Jan Kurek"));
	}

	@After
	public void tearDown() throws Exception {
		dbClient.deleteAllClient();
	}

	@Test
	public void testAddClient() {
		dbClient.addClient(new Client("Piotr Glinko"));
		assertEquals(2, dbClient.getAllClients().size());
	}

	@Test
	public void testGetAllClients() {
		dbClient.addClient(new Client("Piotr Glinko"));
		dbClient.addClient(new Client("Arek Koziol")); 
		assertEquals(3, dbClient.getAllClients().size());
	}

	@Test
	public void testDeleteAllClient() {
		dbClient.addClient(new Client("Piotr Glinko"));
		dbClient.addClient(new Client("Arek Koziol")); 
		assertEquals(3, dbClient.getAllClients().size());
		dbClient.deleteAllClient();
		assertEquals(0, dbClient.getAllClients().size());
	}

	@Test
	public void testFindClientByName() {
		dbClient.addClient(new Client("Piotr Glinko"));
		dbClient.addClient(new Client("Arek Koziol")); 
		assertEquals(1, dbClient.findClientByName("Piotr Glinko").size());
		assertTrue(dbClient.findClientByName("Piotr Glinko").size() == 1);
	}

	@Test
	public void testDeleteClient() {
		dbClient.addClient(new Client("Piotr Glinko"));
		dbClient.addClient(new Client("Arek Koziol")); 
		assertTrue(dbClient.getAllClients().size() == 3);
		dbClient.deleteClient(dbClient.findClientByName("Arek Koziol")); 
		assertTrue(dbClient.getAllClients().size() == 2);
	}

}
