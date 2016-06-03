package mypack;

import java.io.*;

class RandomAccessDemo {
	public static void main(String args[]){
		double data[] = {19.4, 10.2, 123.54, 33.0, 39.4, 98.54, 784.08, 6.34, 9.0, 469.0};
		double d;
		
		//Open and use a random access file
		try(RandomAccessFile raf = new RandomAccessFile("random.dat", "rw")){
			//Write values to the file
			for(int i=0; i<data.length; i++){
				raf.writeDouble(data[i]);
			}
			
			//Now, read back specific values
			raf.seek(0); //seek the first double value
			d = raf.readDouble();
			System.out.println("First value is "+d);
			
			raf.seek(8); //seek the second double value
			d = raf.readDouble();
			System.out.println("Second value is "+d);
			
			raf.seek(8*3); //seek the fourth double value
			d = raf.readDouble();
			System.out.println("Fourth value is "+d);
			
			System.out.println();
			
			//Now, read every other value
			System.out.println("Here is every other values: ");
			for(int i =0; i < data.length; i=i+2){
				raf.seek(8*i); //seek to ith double value
				d = raf.readDouble();
				System.out.println(d+" ");
			}
		}catch(IOException ext){
			System.out.println("I/O Error: "+ext);
		}
	}
}
