package kat;


	public class Book {
		
	//	private String genre;
		
		public String title;
		
		private double price;
		
		public void setPrice(double price) throws PriceBelowZeroException
		{
			if (price <0)
					throw new PriceBelowZeroException("cena nie moze byc ujemna");
			else System.out.println("ok");
		}
		
		public Book(String title)
		{
			this.title= title;
		}
		
		public Book(String title, Double price) 
		{
			this.title= title;
			this.price= price;

		}
		
		public void printBooks()
		{
			System.out.println(title + "\tCena: " + price + "PLN" );
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