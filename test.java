class testA {
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
	
	public static void main(String[] args){
		try{
			newExcep();
		}catch(Exception e){
			System.out.println("Catch exception in main");
		}
	}
	
}
	