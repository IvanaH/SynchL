package mypack;

import java.io.*;

class CopyFile {
	public static void main(String args[]){
		int i;
		FileOutputStream fo = null;
		FileInputStream fi = null;
		
		if(args.length != 2){
			System.out.println("Usage: CopyFile FromFile ToFile");
			return;
		}
		
		try{
			fi = new FileInputStream(args[0]);
		}catch(FileNotFoundException ext){
			System.out.println("The original file not found");
			return;
		}
		
		try{
			fo = new FileOutputStream(args[1]);
		}catch(FileNotFoundException ext){
			System.out.println("Can not create the new file");
			return;
		}
		
		try{
			do{
				i = fi.read();
				if(i!=-1)
					fo.write(i);
			}while(i!=-1);
		}catch(IOException ext){
			System.out.println("Error occurred: "+ext);
		}
		
		finally{
			try{
				if(fo != null)
					fo.close();
				if(fi != null)
					fi.close();				
			}catch(IOException ext){
				System.out.println("Error closing file");
			}			
		}

	}

}
