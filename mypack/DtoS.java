package mypack;

import java.io.*;

class DtoS {
	public static void main(String args[]){
		String s;
		
		try(BufferedReader br = new BufferedReader(new FileReader("text.txt"))){
			while((s = br.readLine()) != null){
				System.out.println(s);
			}
		}catch(IOException ext){
			System.out.println("I/O Error: "+ext);
		}
	}
}
