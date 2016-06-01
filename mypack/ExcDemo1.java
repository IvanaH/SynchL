package mypack;

class ExcDemo1 {
	public static void main(String args[]){
		int nums[] = new int [4];

		//JVM’s default exception handler
		//Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 7
		//at mypack.ExcDemo1.main(ExcDemo1.java:7)
//		nums[7] = 10;	//Annotate it then catch won't be executed
//		System.out.println("This won't be displayed.");
		
		try{
			System.out.println("Before exceptions is generated.");
			
			nums[7] = 10;	//Annotate it then catch won't be executed
			System.out.println("This won't be displayed.");
		}
		catch(ArrayIndexOutOfBoundsException exc){
			//catch the exception
			System.out.println("Index out-of bound.");
		}
		System.out.println("After catch statement.");
	}
}
