package thinkingIJ;

class EverythingIsAnObject {
	public static void main(String[] args) {
		DefaultInitialization di = new DefaultInitialization();
		di.printPara();
		
		System.out.println();
		
		StaticDemo sd1 = new StaticDemo();
		StaticDemo sd2 = new StaticDemo();
		sd1.printI();
		sd2.printI();
		sd1.setI(15);
		sd1.printI();
		sd2.printI();
		
	}
}

class DefaultInitialization{
	int i;
	char c;
	
	void printPara(){
		System.out.println("Value of int: " + i);
		System.out.println("Value of char: " + c);
		
//		int ii;
//		char cc;
//		
//		System.out.println(ii);  //"The local variable ii may not have been initialized"
//		System.out.println(cc);  //"The local variable cc may not have been initialized"
	}
}


class StaticDemo{
	static int i = 10;
	
	void setI(int x){
		i = x;
	}
	
	void printI(){
		System.out.println("The value of static para i is: "+i);
	}
}
