package thinkingIJ;

import java.util.Scanner;

class FibonacciS {
	public static void main(String[] args) {
		FibonacciS fs = new FibonacciS();
		fs.genFibonacci(20);		
	}
	
	int getNum(){
		Scanner s = null;
		System.out.println("Please enter an integer: ");
		int i = s.nextInt();
		return i;
	}
	
	void genFibonacci(int i){
		int m =1;
		int n =1;
		switch (i) {
		case 1:
			System.out.println(m);
			break;
		default:
			System.out.print(m+" "+n+" ");
			for(int j=0; j<i-2;j++){
				System.out.print(m+n+" ");
				m = n;
				n = m+n;
			}
			break;
		}
	}

}
