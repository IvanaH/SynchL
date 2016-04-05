class Sup{
	void who(){
		System.out.println("who() in Sup.");
	}
	final void F(){
		System.out.println("This is a final method.");
	}
}
class Sub1 extends Sup{
	void who(){
		System.out.println("who() in Sub1.");
	}
//	void F(){         //Cannot override the final method
//	}
}
//abstract class Sub2 extends Sup{
class Sub2 extends Sup{
	void who(){
		System.out.println("who() in Sub2.");
	}
	
//	abstract void area();  //must be overriden by subclass
}

class DynDispDemo {
	public static void main(String args[]){
		Sup superob = new Sup();
		Sub1 subob1 = new Sub1();
		Sub2 subob2 = new Sub2();  // Cannot instantiate an abstract class
		
		Sup supRef;
		
		supRef = superob;
		supRef.who();
		
		supRef = subob1;
		supRef.who();
		
		supRef = subob2;
		supRef.who();
	}

}
