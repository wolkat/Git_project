package com.kat.bookstore.events;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;

public class ChangeShelf implements IBookProcesses {

	private static Logger logger = Logger.getLogger(ChangeShelf.class);
    Calendar calendar = GregorianCalendar.getInstance();
	

	public void processBook(BookEvent event) {
		//event.get_book().setWhichShelf();
		logger.info(event.get_book().getTitle() + " - zmiana miejsca " + calendar.getTime());

	}

}
