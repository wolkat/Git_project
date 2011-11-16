package kat.events;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;

public class ChangeShelf implements IBookProcesses {

	private static Logger logger = Logger.getLogger(ChangeShelf.class);
    Calendar calendar = GregorianCalendar.getInstance();
	
	@Override
	public void processBook(BookEvent bookEvent) {
		//event.get_book().setWhichShelf("Nowosci");
		//logger.info(event.get_book().getName() + " - zmiana miejsca " + calendar.getTime());

	}

}
