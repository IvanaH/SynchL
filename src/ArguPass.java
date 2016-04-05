class Test{
	int a, b;
	
	Test(int i, int j){
		a = i;
		b = j;
	}
	
	//This method causes no change to the arguments used in the call
	void NoChange(int i, int j){
		i = i + j;
		j = -j;
	}
	
	//Pass an object, ob.i and ob.j in object used in the call will changed
	void Change(Test ob){
		ob.a = ob.a + ob.b;
		ob.b = -ob.b;
	}
}

class ArguPass {
	public static void main(String args[]){
		Test ob = new Test(15, 20);
						
		System.out.println("a and b before call: "+ ob.a+ " "+ob.b);
		
		
		ob.NoChange(ob.a, ob.b);
		System.out.println("a and b after call: "+ ob.a + " "+ob.b);
		ob.Change(ob);
		System.out.println("a and b after call: "+ ob.a + " "+ob.b);


	}

}
