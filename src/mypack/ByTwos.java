package mypack;

class ByTwo implements Series {
	int start;
	int val;
	int prev;
	
	ByTwo(){
		start = val = 0;
	}
	
	public int getNext(){
		prev = val;
		val = val + 2;
		return val;
	}
	
	public void reset(){
		val = start;
		prev = start - 2;
	}
	
	public void setStart(int x){
		start = x;
		val = x;
		prev = x - 2;
	}
	
	int getPrevious(){
		return prev;
	}
}

class ByThree implements Series {
	int start;
	int val;
	
	ByThree(){
		start = val = 0;
	}
	
	public int getNext(){
		val = val + 3;
		return val;
	}
	
	public void reset(){
		val = start;
	}
	
	public void setStart(int x){
		start = x;
		val = x;
	}
}

class ByTwos{
	public static void main(String args[]){
		ByTwo ob = new ByTwo();
		
		for(int i = 0; i<5;i++)
			System.out.println("Next value is "+ob.getNext());
		
		System.out.println("\nResetting");
		ob.reset();
		for(int i = 0; i<5;i++)
			System.out.println("Next value is "+ob.getNext());
		
		System.out.println("\nStarting at 100");
		ob.setStart(100);
		for(int i = 0; i<5;i++)
			System.out.println("Next value is "+ob.getNext());
		System.out.println("\n");
		
		ByTwo tob = new ByTwo();
		ByThree thob = new ByThree();
		Series sob;
		
		for(int i = 0; i<5;i++){
			sob = tob;
			System.out.println("Next ByTwo value is "+ sob.getNext());
//			System.out.println("Previous ByTwo value is "+ sob.getPrevious()); //like super-subclasss, 
			                                                                   //getPrevious() is undefined for the type Series
			sob = thob;
			System.out.println("Next ByThree value is "+ sob.getNext());			
		}		
	}
}
