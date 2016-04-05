class MyClassII{
	int x;
	
	MyClassII(){
		System.out.println("Inside MyClassII().");
		x= 0 ;
	}
	
	MyClassII(int i){
		System.out.println("Inside MyClassII(int).");
		x = i;
	}
	
	MyClassII(double d){
		System.out.println("Inside MyClassII(double).");
		x = (int)d;
	}
	
	MyClassII(int i, int j){
		System.out.println("Inside MyClassII(int, int).");
		x = i*j;
	}
}

class OverloadConsDemo {
	public static void main(String args[]){
		MyClassII t1 = new MyClassII();
		MyClassII t2 = new MyClassII(88);
		MyClassII t3 = new MyClassII(17.33);
		MyClassII t4 = new MyClassII(2,4);
		
		System.out.println("t1.x: "+ t1.x);
		System.out.println("t2.x: "+ t2.x);
		System.out.println("t3.x: "+ t3.x);
		System.out.println("t4.x: "+ t4.x);
	}
}
