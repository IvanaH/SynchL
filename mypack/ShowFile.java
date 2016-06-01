package mypack;

import java.io.*;

class ShowFile {
	public static void main(String args[]){
		int i;
		FileInputStream fn;
	
		//First make sure that a file has been specified
		if(args.length != 1){
			System.out.println("Usage: ShowFile Filename");
			return;
		}
		
		try{
			fn = new FileInputStream(args[0]);
		}catch(FileNotFoundException ext){
			System.out.println("File not found.");
			return;
		}
		
		
		//read file until EOF encountered
		try{
			do{
				i = fn.read();
				if(i!=-1)
					System.out.println((char)i);
			}while(i!=-1);
		}catch(IOException ext1){
			System.out.println("Error reading file");
		}
		
		try{
			fn.close();
		}catch(IOException ext2){
			System.out.println("Error closing file");
		}
	}
	
}

