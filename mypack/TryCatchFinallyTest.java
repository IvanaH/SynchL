package mypack;

class TryCatchFinallyTest {
	public static void newExcep(){
		try{
			System.out.println("Throw a new exception");
			throw new IllegalAccessError();
		}catch(IndexOutOfBoundsException e){
			System.out.println("Get IndexOutOfBoundsException");
		}
		
		finally {
			System.out.println("Finally block");
		}
	}

	public static int newReturn(){
		try{
			System.out.println("return/exception in try");
			return 1/0;
		}catch(IndexOutOfBoundsException e){
			System.out.println("Get IndexOutOfBoundsException");
		}
		
		finally {
			System.out.println("return in finally");
			return 1;
		}
	}
	
	public static void main(String args[]){
		try{
//			newExcep();
			System.out.println("return is "+ newReturn());
		}catch(IllegalAccessError e){
			System.out.println("Catch exception in main");
		}
	}
}
