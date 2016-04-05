package trespack;

public class BookDos {
//	private String title;
//	private String author;
//	private int pubDate;

	protected String title;
	protected String author;
	protected int pubDate;
	
	public BookDos(String t, String a, int p){
		title = t;
		author = a;
		pubDate = p;
	}
	
	public void show(){
		System.out.println(title);
		System.out.println(author);
		System.out.println(pubDate);
	}
}

