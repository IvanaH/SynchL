package mypack;

class ExcTest{
	int nums[] = new int[3];	
	
	void genException(){
		System.out.println("Before exception is generated.");

		//generate an index out-of-bound exception
//		nums[10] = 0;
//		System.out.println("This won't be displayed.");	
	
		//catching by itself, the the main won't catch it
		try{
			nums[10] = 0;
			System.out.println("This won't be displayed.");	
		}
		catch(ArrayIndexOutOfBoundsException exc){
			System.out.println("Catching by itself");
		}
	}	
}

class ExcDemo2 {
	public static void main(String args[]){
		try{
			ExcTest ob = new ExcTest();
			ob.genException();			
		}
//		catch(ArithmeticException exc){  //won't work
//			System.out.println("Index of bounds.");
//		}
		catch(ArrayIndexOutOfBoundsException exc){
			System.out.println("Index of bounds.");
		}
		System.out.println("After catch statement.");
	}

}
