package kat.events;
	
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;


public class CleanBookShelf implements IBookProcesses {

		private static Logger logger = Logger.getLogger(CleanBookShelf.class);
	    Calendar calendar = GregorianCalendar.getInstance();

	
		public void processBook(BookEvent event) {
			event.get_book().setCleanShelf(true);
			logger.info(event.get_book().getTitle() + " - polka wyczyszczona o " + calendar.getTime());

		}

	
}
