package mypack;

class NestTry {
	public static void main(String args[]){
		int nums1[] = {4,25,6,8,102,0,56,11,48,489};
		int nums2[] = {2,11,5,0,54,4,17};
		
		try{   // outer try
			for(int i=0; i<nums1.length;i++){
				try{  //nested try
					System.out.println(nums1[i]+"/"+nums2[i] +" is " +nums1[i]/nums2[i]);
				}
				catch(ArithmeticException ext){
					System.out.println("Can't divide by zero.");
				}
			}
		}catch(ArrayIndexOutOfBoundsException ext){ 
			System.out.println("No matching element found.");
			System.out.println("Fatal error - program terminated.");
		}
	}
}
