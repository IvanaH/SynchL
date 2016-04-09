package mypack;

class ExcDemo3 {
	public static void main(String args[]){
		int nums1[] = {4,5,6,8,2,0,56};
		int nums2[] = {2,0,5,54,0,7};
		
		for(int i=0; i<nums1.length;i++){
			try{
				System.out.println(nums1[i]+"/"+nums2[i] +" is " +nums1[i]/nums2[i]);
			}
//			catch(ArithmeticException ext){
//				System.out.println("Can't divide by zero.");
//			}
//			catch(ArrayIndexOutOfBoundsException ext){
//				System.out.println("Index out-of bound.");
//			}
			catch(final ArithmeticException|ArrayIndexOutOfBoundsException ext){
				System.out.println("Multi-catch");
				System.out.println("Exception caught: "+ext);
			}
		}
	}
}
