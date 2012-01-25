package com.kat.bookstore.events;

import org.apache.log4j.Logger;


public class EmptyShelf implements IBookProcesses {

	private static Logger logger = Logger.getLogger(EmptyShelf.class);
   
	@Override
	public void processBook(BookEvent event) {
		event.get_book().setOnShelf(true);
		logger.info(event.get_book().getTitle() + " - the shelf is now empty");

	}

}
