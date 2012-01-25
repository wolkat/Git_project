package com.kat.bookstore.services;

import com.kat.bookstore.domain.Book;

public abstract class Condition {

	public abstract boolean getCondition(Book b);	
	
}
