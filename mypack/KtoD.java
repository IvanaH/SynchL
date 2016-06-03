package mypack;

import java.io.*;

class KtoD {
	public static void main(String args[])
	 throws IOException{
		String str;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Plz enter text('stop' to quit)");
		
//		FileWriter fw;
//		try{
//			fw = new FileWriter("test.txt");
//		}catch(IOException exc){
//			System.out.println("Error occurred: " + exc);
//			return;
//		}
//		
		FileWriter fw = new FileWriter("test.txt");
		
		try{
			do{
				System.out.print(": ");
				str = br.readLine();
				
				if(str.compareTo("stop") == 0)
					break;
				
				str = str + "\r\n"; // add new line
				
				fw.write(str);
				
			}while(str.compareTo("stop") != 0);
		}catch(IOException exc){
			System.out.println("Error occurred: " + exc);
		}

		finally{
			fw.close();
		}
	}
}

//class KtoD {
//	public static void main(String args[]){
//		String str;
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Plz enter text('stop' to quit)");		
//		
//		try(FileWriter fw = new FileWriter("test.txt")){
//			do{
//				System.out.print(": ");
//				str = br.readLine();
//				
//				if(str.compareTo("stop") == 0)
//					break;
//				
//				str = str + "\r\n"; // add new line
//				
//				fw.write(str);
//				
//			}while(str.compareTo("stop") != 0);
//		}catch(IOException exc){
//			System.out.println("Error occurred: " + exc);
//		}
//	}
//}
