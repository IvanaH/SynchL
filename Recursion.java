class Factorial{
	//This is a recursive function.
	int factR(int n){
		int result;
		if(n==1)
			return 1;
		result = factR(n-1) * n;
		System.out.println(result);
		return result;
	}
	
	//This is an iterative equivalent.
	int factI(int n){
		int t, result;
		
		result = 1;
		for(t=1;t<=n;t++){
			result = result * t;
		    System.out.println(result);
		}
		return result;
	}
}

class Recursion {
	public static void main(String args[]){
		Factorial f = new Factorial();
		
		System.out.println("Fatorials using recursive method.");
		System.out.println("Factorial of 30 is " + f.factR(30));
//		System.out.println("Factorial of 4 is " + f.factR(4));
//		System.out.println("Factorial of 5 is " + f.factR(5));
//		System.out.println();
//		
//		System.out.println("Fatorials using iteractive method.");
//		System.out.println("Factorial of 3 is " + f.factI(3));
//		System.out.println("Factorial of 4 is " + f.factI(4));
//		System.out.println("Factorial of 5 is " + f.factI(5));
//		System.out.println();
	}

}
