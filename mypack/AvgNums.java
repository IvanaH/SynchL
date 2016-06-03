package mypack;

import java.io.*;

class AvgNums {
	public static void main(String args[])
	  throws IOException{
		
		BufferedReader br = new 
				BufferedReader(new InputStreamReader(System.in));
		
		String str;
		int n;
		double sum = 0.0;
		double avg,t;
		
		do{
			System.out.println("How many nums will you enter?");
			str = br.readLine();
			
			try{
				n = Integer.parseInt(str);				
			}catch(NumberFormatException ext){
				System.out.println("Invaild format.");
				return;
			}
		}while(n<=0);
		

		
		System.out.println("Enter "+n+" values.");
		for(int i=0; i<n; i++){
			do{
				System.out.print(": ");
				str = br.readLine();
				try{
					t = Double.parseDouble(str);
				}catch(NumberFormatException ext){
					System.out.println("Invaild format.");
					t = 0.0;
				}
			}while(t == 0.0);
			
			sum = sum+t;
		}
		avg = sum/n;
		System.out.println("Average is: " + avg);
	}
}
