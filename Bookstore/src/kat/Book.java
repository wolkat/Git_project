package kat;


	public class Book {
		
		public String title;
		public String author;
		private double price;
		
		public void setPrice(double price) throws PriceBelowZeroException
		{
			if (price <0)
					throw new PriceBelowZeroException("cena nie moze byc ujemna");
			else this.price=price;
		}
		
		public Book(String title, String author, BookGenre genre, Double price)
		{
			this.title= title;
			this.author=author;
			this.price=price;
		}
		
		public Book(String title, String author, BookGenre genre)
		{
			this.title= title;
			this.author=author;
		}
		
		public Book(String title, String author) 
		{
			this.title= title;
			this.author= author;

		}
		
		public void printBooks()
		{
			System.out.println("Tytul: "+title + "\tAutor: "+ author + "\tCena: " + price + "PLN" );
		}


		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Integer cena) {
			this.price = cena;
		}
	}