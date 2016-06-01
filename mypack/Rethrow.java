package mypack;

class Rt{
	void genExceotion(){
		int nums1[] = {4,25,6,8,102,0,56,11,48};
		int nums2[] = {2,11,5,0,54,4,17};
		
		for(int i=0; i<nums1.length;i++){
			try{
				System.out.println(nums1[i]+"/"+nums2[i] +" is " +nums1[i]/nums2[i]);
				throw new ArithmeticException();
			}
			catch(ArrayIndexOutOfBoundsException ext){  
				System.out.println("No matching element found.");
			}catch(ArithmeticException ext){   
				System.out.println("Can't divide by zero.");
				throw ext;
			}
		}		
	}
}

class Rethrow {
	public static void main(String args[]){
		Rt ob = new Rt();
		
		try{
			ob.genExceotion();		
		}
		catch(ArithmeticException ext){   
			System.out.println("Fatal error - program terminated.");
		}
	}
}
