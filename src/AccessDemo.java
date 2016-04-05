class MyClass{
	private int alpha;
	public int beta;
	int gamma;
	
	void setAlpha(int a){
		alpha = a;
	}
	int getAlpha(){
		return alpha;
	}
}

class AccessDemo {
	public static void main(String args[]){
		MyClass ob = new MyClass();
		
		ob.setAlpha(894);
		System.out.println("ob.setAlpha is "+ ob.getAlpha());
		
//		ob.alpha = 10; //Wrong! Alpha is private.
		
		ob.beta = 20;
		ob.gamma = 34;
	
	}
}
