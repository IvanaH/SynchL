class FDemo{
	int x;
	
	FDemo(int i){
		x=i;
	}
	
	//called when object is recycled
	protected void finalize(){
		System.out.println("Finializing "+ x);
	}
	
	//generates an object that is immediately destroyed
	void genarator(int i){
		FDemo o = new FDemo(i);
	}
}


class Fnialize {
	public static void main(String args[]){
		int count;
		
		FDemo ob = new FDemo(0);
		
		/*Now, generate a large number of objects. At some 
		 * point, garbage collection will occur.
		 * Note: Might need to increase the number of objects
		 * generated in order to force garbage collection. */
		
		for(count=1; count < 1000000; count++){
			ob.genarator(count);
		}
	}

}
