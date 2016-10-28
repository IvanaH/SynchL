package thinkingIJ;

import org.omg.CORBA.SystemException;

class BreakAndContinue {
	public static void main(String[] args) {
		for(int i=0;i < 100; i++){
			if(i == 73) 
				break;  //out of loop
			if(i%7 != 0)
				continue; //top of loop
			System.out.print(i+" ");			
		}
		
		System.out.println();
		int i = 0;
		
		while(true){
			i++;
			int j=i*29;
			if( j== 2681){
				System.out.print("i ");
				break; //out of loop
			}
			
			if(j%27 != 0)	
				continue; // top of loop
			System.out.print(i+" ");	
		}
	}
}
