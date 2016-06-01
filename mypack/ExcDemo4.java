package mypack;

class ExcDemo4 {
	public static void main(String args[]){
		int nums1[] = {4,25,6,8,102,0,56,11,48};
		int nums2[] = {2,11,5,0,54,4,17};
		
		for(int i=0; i<nums1.length;i++){
			try{
				System.out.println(nums1[i]+"/"+nums2[i] +" is " +nums1[i]/nums2[i]);
				throw new ArithmeticException();
			}
			catch(ArrayIndexOutOfBoundsException ext){  //catch subclass
				System.out.println("No matching element found.");
			}catch(Throwable ext){   //catch superclass
				System.out.println("Some exception occurred.");
			}
		}
	}
}
