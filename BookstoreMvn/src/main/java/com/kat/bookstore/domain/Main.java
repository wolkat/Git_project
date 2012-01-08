package com.kat.bookstore.domain;
import org.apache.log4j.*;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		PropertyConfigurator.configure("Log4J.properties");
		logger.info("Main works");

	}

}
