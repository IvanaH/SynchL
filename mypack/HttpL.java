package mypack;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;


class HttpL {
	public static void main(String args[]){
		try{
			if(args.length != 1){
				System.out.println("Usage: HttpL url");
				return;
			}
			
			URL url = new URL(args[0]);
			
			HttpURLConnection con = (HttpURLConnection )url.openConnection();
			
			System.out.println(con.getConnectTimeout());
			System.out.println(con.getContentType());
			System.out.println(con.getContent());
			System.out.println(con.getDate());
			System.out.println(con.getContentEncoding());
			System.out.println(con.getReadTimeout());
			System.out.println();
			
			con.setReadTimeout(10*1000);
			con.connect();
			System.out.println(con.getRequestMethod());
			System.out.println(con.getResponseCode());
			System.out.println(con.getResponseMessage());

		}catch(MalformedURLException exc){
			System.out.println("Some error occurred when get URL: "+exc);
		}catch(SocketTimeoutException exc){
			System.out.println("Timeout Exceptions: "+exc);		
		}catch(IOException exc){
			System.out.println("I/O Exceptions: "+exc);
		}
	}
}
