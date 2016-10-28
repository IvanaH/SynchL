package thinkingIJ;

import java.util.Scanner;

class tellPrime {
	public static void main(String[] args) {
//		Scanner s = null;
//		int i = s.nextInt();
		tellP tp = new tellP();
		tp.tell(99);
	}	
}

class tellP {
	void tell(int k){
		if(k<0)
			System.out.println("There is no prime under 0.");
		else if (k<2)
			System.out.println(k + " is not a prime number.");
		else {
			int x = 2;
			for(int i=2; i<=k; i++){
				int j=2;
				for(;j<=i;){
				    if( i%j != 0)
						j++;
					else if (i == j){
						System.out.println( i + " is a prime number."+ "And the distance is "+ (j-x));
						x=j;
				        j++;
				    }
					else{
//						System.out.println( i + " is not a prime number.");
						break;
					}
				}
			}
		}
	}
}
