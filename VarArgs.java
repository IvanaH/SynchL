
class VarArgs {
	// msg is a normal parameter and v is a vararges parameter
	// static void vaTest1(int ... v, String msg)  //The variable argument type int of the method vaTest1 must be the last
	// static void vaTest1(String ... msg, int ... v) //There must be only one varargs parameter.
	static void vaTest(String msg, int ... v){
		System.out.println(msg + v.length);
		System.out.println("Content: ");
		
		for (int i = 0; i < v.length;i++)
			System.out.println(" arg" + i+": "+v[i]);
		
		System.out.println();
	}
	
	public static void main(String args[]){
		vaTest("One vararg: ",10);
		vaTest("Three vararg: ",1,3,5);
		vaTest("No vararg: ");
	}
}
