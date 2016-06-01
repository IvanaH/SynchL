package mypack;

interface MyIF{
	//normal interface method
	int getUserID();
	
	//default interface method
	default int getAdminID(){
		return 1;
	}
	
	//static interface method
	static int getUniversalID(){
		return 0;
	}
}

class MyIFImp implements MyIF{
	// can just implement he getUserID(), considering the getAdminID() is a default method
	public int getUserID(){
		return 100;
	}

	//static interface methods are not inherited by a subinterface.
//	static int getUniversalID(){
//		return 2;
//	}
}


class MyIFImp2 implements MyIF{
	public int getUserID(){
		return 200;
	}
	
	public int getAdminID(){
		return 2;
	}
}

class DefaultMethodDemo {
	public static void main(String args[]){
		int uID = MyIF.getUniversalID();
		
		MyIFImp ob = new MyIFImp();
		
		System.out.println("Universal ID is "+ uID);

		//static interface methods are not inherited by either an implementing class.
//		System.out.println("Universal ID is "+ ob.getUniversalID());
		
		System.out.println("User ID is "+ ob.getUserID());
		System.out.println("Admin ID is "+ ob.getAdminID());
		
		MyIFImp2 ob2 = new MyIFImp2();
		
		System.out.println("User ID is "+ ob2.getUserID());
		System.out.println("Admin ID is "+ ob2.getAdminID());
	}

}
