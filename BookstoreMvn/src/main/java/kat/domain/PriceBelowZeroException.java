
package kat.domain;

public class PriceBelowZeroException extends Exception {

	private static final long serialVersionUID = 1L;

	public PriceBelowZeroException(String message) {
		super(message);
		
	}

}
