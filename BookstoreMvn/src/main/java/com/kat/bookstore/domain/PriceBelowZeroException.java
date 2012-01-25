package com.kat.bookstore.domain;

@SuppressWarnings("serial")
public class PriceBelowZeroException extends Exception {

	public PriceBelowZeroException(String message) {
		super(message);

	}

}
