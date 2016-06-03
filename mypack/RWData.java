package mypack;

import java.io.*;

class RWData {
	public static void main(String args[]){
		int i = 10;
		double d = 1023.45;
		boolean b = true;
		
		//write some value
		try(DataOutputStream dataout = new DataOutputStream(new FileOutputStream("testdata"))){
			System.out.println("Writing "+i);
			dataout.writeInt(i);
			
			System.out.println("Writing "+d);
			dataout.writeDouble(d);
			
			System.out.println("Writing "+b);
			dataout.writeBoolean(b);
			
			System.out.println("Writing "+12.2*7.4);
			dataout.writeDouble(12.2*7.4);
		}catch(IOException ext){
			System.out.println("Write Error");
			return;
		}
		
		System.out.println();
		
		//Now read back
		try(DataInputStream dataIn = new DataInputStream(new FileInputStream("testdata"))){
			i = dataIn.readInt();
			System.out.println("Reading "+i);
			
			d = dataIn.readDouble();
			System.out.println("Reading "+d);
			
			b = dataIn.readBoolean();
			System.out.println("Reading "+b);
			
			d = dataIn.readDouble();
			System.out.println("Reading "+d);
			
//			System.out.println("Reading: " + dataIn.read());
//			System.out.println("Reading: " + dataIn.readLine());

		}catch(IOException ext){
			System.out.println("Read Error");
			return;
		}
	}
}
