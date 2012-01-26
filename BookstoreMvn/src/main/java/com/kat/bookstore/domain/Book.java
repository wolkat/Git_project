package com.kat.bookstore.domain;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Book {

	public String title;
	public String author;
	public BookGenre genre;
	public int year;
	public int price;
	public boolean emptyShelf;
	public boolean ordered;
	

	public Book(String title, String author, BookGenre genre, int price) 
	{
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.price = price;
		this.emptyShelf = true;
		this.ordered = false;
	}

	public void printBook() {
		System.out.println("Title: " + title + "\tAuthor: " + author + 
				"\tGenre: " + genre	+ "\tPrice: " + price);
	}

	@Min(1)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Min(1)
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public BookGenre getGenre() {
		return genre;
	}

	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	@NotNull
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isOnShelf() {
		return emptyShelf;
	}

	public void setOnShelf(boolean emptyShelf) {
		this.emptyShelf = emptyShelf;
	}

	public boolean isOrdered() {
		return ordered;
	}

	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

}
