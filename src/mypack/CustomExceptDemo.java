package mypack;

class NonIntResultException extends Exception{
	int n;
	int d;
	
	NonIntResultException(int i, int j){
		n = i;
		d = j;
	}
	
	public String toString(){
		return "Result of "+ n +" / "+d+" is non-integer.";
	}
}

class CustomExceptDemo {
	public static void main(String args[]){
		int n1[] = {4,8,5,32,23,456,432,566};
		int n2[] = {2,0,4,4,0,8};
		
		for(int i=0; i<n1.length;i++){
			try{
				if(n1[i] % 2 != 0)
					throw new NonIntResultException(n1[i],n2[i]);
				
				System.out.println(n1[i] + " / " + n2[i] + " is " + n1[i]/n2[i]);
			}
			catch(ArithmeticException ext){
				System.out.println("Can't divide by zero.");
			}
			catch(ArrayIndexOutOfBoundsException ext){
			System.out.println("No matching element found.");
			}
			catch(NonIntResultException ext){
				System.out.println(ext);
			}
		}
	}
}
