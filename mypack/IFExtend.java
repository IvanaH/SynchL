package mypack;

interface A{
	void meth1();
	void meth2();
	default void meth4(){
		System.out.println("Implement A.meth4().");
	};
}

interface B extends A{
	void meth3();
		
	default void meth4(){
		System.out.println("Implement B.meth4().");
	};
}


// If there is not inheritance between A and B,  and the class DO　 NOT override the default method meth4(),
// then compile-time error will occurred:
// Duplicate default methods named meth4 with the parameters () and () are inherited from the types A and B
class MyClass implements B,A {  //remove meth1() will cause a compile-time error
	public void meth1(){
		System.out.println("Implement meth1().");
	}
	public void meth2(){
		System.out.println("Implement meth2().");
	}
	public void meth3(){
		System.out.println("Implement meth3().");
	}
	
	//Class implementation takes priority over an interface default implementation
//	public void meth4(){
//		System.out.println("Implement MyClass.meth4().");
//	}
}

class IFExtend{
	public static void main(String args[]){
		MyClass ob = new MyClass();
		
		ob.meth1();
		ob.meth2();
		ob.meth3();
		ob.meth4();
	}
}
