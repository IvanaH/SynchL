package mypack;

import java.io.*;

class ShowFileII {
	public static void main(String args[]){
		int i;
		FileInputStream fn = null;
	
		//First make sure that a file has been specified
		if(args.length != 1){
			System.out.println("Usage: ShowFile Filename");
			return;
		}
		
		try{
			fn = new FileInputStream(args[0]);
			
			do{
				i = fn.read();
				if(i!=-1)
					System.out.println((char)i);
			}while(i!=-1);
			
		}catch(FileNotFoundException ext){
			System.out.println("File not found.");
			return;
		}catch(IOException ext){
			System.out.println("Error reading file");
//		}catch(IOException ext){
//			System.out.println("Error message: "+ ext);
		}
	
				
		//close file on the way out of the try block
		finally{
			try{
				if(fn != null)
					fn.close();
			}catch(IOException exc){
				System.out.println("Error closing file");
			}
		}
		
	}
}
