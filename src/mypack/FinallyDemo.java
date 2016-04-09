package mypack;

class UseFinally{
	void genEx(int what){
		int t = 4;
		int nums[] = new int[2];
		
		System.out.println("Receiving " + what);
		
		try{
			switch(what){
			case 0:
				t = 10/what;  //generate div-by-zero error
				break;
			case 1:
				nums[4] = t;  //generate out-of-index error
				break;
			case 2:
				System.out.println("No exception. Try block end normally and then Return.");
				return; //return from try block
			}
		}
		catch(ArithmeticException exc){
			System.out.println("Can't divide by zero.");
			System.out.println("Catching exception. catch block end normally and then Return");
			return;  //return from catch block
		}
		catch(ArrayIndexOutOfBoundsException ext){  
			System.out.println("No matching element found.");
		}
		
		finally{
			System.out.println("Leaving try.");
			System.out.println();
		}
	}
}

class FinallyDemo {
	public static void main(String args[]){
		UseFinally ob = new UseFinally();
		
		for(int i=0; i<3; i++){
			ob.genEx(i);
		}
	}
}
