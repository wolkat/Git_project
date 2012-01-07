package com.kat.bookstore.domain;

public class Book {
	
		public long id;
		public String title;
		public String author;
		private double price;
		public int year;
		public BookGenre genre;
		public boolean cleanShelf;
		public ShelfName whichShelf;
		
		public Book(){}
		
		public Book(String title, String author, BookGenre genre, int year, double price)
		{
			this.title= title;
			this.author=author;
			this.genre=genre;
			this.year=year;
			this.price=price;
			this.cleanShelf=false;
			this.whichShelf= ShelfName.Newest;
		}
		
		public void printBook()
		{
			System.out.println("Tytul: "+title + "\tAutor: "+ author + "\tCena: " + price + "PLN" );
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

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
		
		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}
		
		public Double getPrice() {
			return price;
		}
		
		public void setPrice(double price) throws PriceBelowZeroException
		{
			if (price <0)
					throw new PriceBelowZeroException("cena nie moze byc ujemna");
			else this.price=price;
		}
		
		public boolean isCleanShelf() {
			return cleanShelf;
		}

		public void setCleanShelf(boolean cleanBox) {
			this.cleanShelf = cleanBox;
		}
		
		public ShelfName getWhichShelf() {
			return whichShelf;
		}

		public void setWhichShelf(ShelfName whichShelf) {
			this.whichShelf = whichShelf;
		}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
	}