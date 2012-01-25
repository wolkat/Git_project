package com.kat.bookstore.events;

import org.apache.log4j.Logger;


public class OrderBook implements IBookProcesses {

	private static Logger logger = Logger.getLogger(OrderBook.class);
   
	@Override
	public void processBook(BookEvent event) {
		event.get_book().setOrdered(true);
		logger.info(event.get_book().getTitle() + " - ordered ");
	}

}
