package mypack;

import trespack.BookDos;

class ExtBook extends BookDos {
	private String publisher;
	
	public ExtBook(String t, String a, int d, String p){
		super(t,a,d);
		publisher = p;		
	}
	
	public void show(){           // - overrides trespack.BookDos.show 
		                          // - Cannot reduce the visibility of the inherited method from
		super.show();
		System.out.println(publisher);
		System.out.println();
	}
	
	protected String getPublisher(){ return publisher;}
	protected void setPublisher(String p){ publisher = p;}
	
	String getTitle(){ return title;}
	void setTitle(String t){ title = t;}
	String getAuthor(){ return author;}
	void setAuthor(String a){ author = a;}
	int getPubdate(){ return pubDate;}
	void setPubdate(int d){ pubDate = d;}	
}

class ProtectDemo {
	public static void main(String args[]){
		ExtBook books[] = new ExtBook[5];
		
		books[0] = new ExtBook("Java: A Beginner's Guide","Schildt",2014,"McGraw-Hill Professional");
		books[1] = new ExtBook("Java: The Complete Reference","Schildt",2014,"McGraw-Hill Professional");
		books[2] = new ExtBook("The Art of Java","Schildt and Holms",2003,"McGraw-Hill Professional");
		books[3] = new ExtBook("Red Storm Rising","Clancy",1986,"Putnam");
		books[4] = new ExtBook("On the Road", "Kerouac",1955,"Viking");
		
		for(int i = 0; i< books.length;i++)
			books[i].show();
		
		//Find books by author
		System.out.println("Showing all books by Schildt:");
		for(int i = 0; i< books.length; i++)
			if(books[i].getAuthor() == "Schildt")
				System.out.println(books[i].getTitle());
		
//		books[0].title = "Test"; // BookDos.title is not visible
	}
}
