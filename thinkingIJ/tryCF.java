package thinkingIJ;


class tryCF {
	public static void main(String[] args) {
		
		tryCF t = new tryCF();
		t.returnBeforefinally();
	}
	
	protected String returnBeforefinally(){
		String s = "return in try";
		int i,n=0,m=0;

		try{			
			System.out.println("Try block");
			
			i = m/n;
			
		}catch (ArithmeticException ext1){
			System.out.println("Catch block");
		}
		
		
//		finally {
//			System.out.println("final block");
//		}
		
		return s;

	}
}
