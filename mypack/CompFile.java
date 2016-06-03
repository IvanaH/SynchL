package mypack;

import java.io.*;

class CompFile {
	public static void main(String args[]){
		int i=0, j=0;
		
		//First make sure that both files has been specified
		if(args.length != 2){
			System.out.println("Usage:  CompFile f1 f2");
			return;
		}
		
		try(FileInputStream f1 = new FileInputStream(args[0]);
			FileInputStream f2 = new FileInputStream(args[1])){
			
			//check the contents of each file
			do{
				i = f1.read();
				j = f2.read();
				if( i!=j)
					break;
			}while( i!=-1 && j!=-1);
			
			
			if( i!=j)
				System.out.println("Files differ.");
			else
				System.out.println("Files are the same.");
					
		}catch(IOException ext){
			System.out.println("I/O Error");
		}
	}

}
