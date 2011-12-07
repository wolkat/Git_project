package kat.events;

import kat.domain.*;

public class BookEvent {

	private Book _book;

	public Book get_book() {
		return _book;
	}

	public void set_book(Book _book) {
		this._book = _book;
	}

	public BookEvent(Object source, Book book) {
		super();
		_book = book;
	}
}
