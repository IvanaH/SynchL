package mypack;

import java.io.*;

class ShowFileIII {
	public static void main(String args[]){
		int i;
	
		//First make sure that a file has been specified
		if(args.length != 1){
			System.out.println("Usage: ShowFile Filename");
			return;
		}
		
		try(FileInputStream fn = new FileInputStream(args[0])){
			do{
				i = fn.read();
				if(i!=-1)
					System.out.println((char)i);
			}while(i!=-1);
			
		}catch(FileNotFoundException ext){
			System.out.println("File not found.");
			return;
		}catch(IOException ext){
			System.out.println("Error occurred: " + ext);
		}		
	}

}
