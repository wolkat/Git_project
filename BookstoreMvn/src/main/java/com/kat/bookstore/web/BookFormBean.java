package com.kat.bookstore.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.kat.bookstore.domain.Book;
import com.kat.bookstore.domain.BookGenre;
import com.kat.bookstore.services.BookDB;


@SessionScoped
@Named("bookBean")
public class BookFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Book b = new Book(null, null, BookGenre.Fantasy , 0);

	private ListDataModel<Book> booklist = new ListDataModel<Book>();

	@Inject
	private BookDB bdb;

	public Book getBook() {
		return b;
	}

	public void setBook(Book game) {
		this.b = game;
	}

	public String addBook() {
		b.setGenre(BookGenre.valueOf(b.getGenre().toString()));
		bdb.addBook(b);
		return "showBooks";
	}

	public ListDataModel<Book> getAllBooks() {
		booklist.setWrappedData(bdb.getAllBooks());
		return booklist;
	}


	public void deleteBook() {
		Book delb = booklist.getRowData();
		bdb.deleteBook(bdb.findBookByTitle(delb.getTitle()));
	}
}
