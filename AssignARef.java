// Assingin array reference variables

class AssignARef {
	public static void main(String[] args){
		int i;
		
		int nums1[] = new int[10];
		int nums2[] = new int[10];
		
		for (i=0;i<10;i++)
			nums1[i] = i;
		
		for (i=0;i<10;i++)
			nums2[i] = -i;
		
		System.out.print("Here is nums1: ");
		for (i=0;i<10;i++)
			System.out.print(nums1[i]+" ");
		System.out.println();

		System.out.print("Here is nums2: ");
		for (i=0;i<10;i++)
			System.out.print(nums2[i]+" ");
		System.out.println();
		System.out.println();

		
		nums2 = nums1; //nums refers to nums1
		
		System.out.print("There is nums2 after assignment: ");
		for (i=0;i<10;i++)
			System.out.print(nums2[i]+" ");
		System.out.println();
		System.out.println();

		for (i=5;i<10;i++)
			nums2[i] = 100 -i;
		
		System.out.print("Here is nums2 after assignment and change: ");
		for (i=0;i<10;i++)
			System.out.print(nums2[i]+" ");
		System.out.println();
		
		System.out.print("Here is nums1 after change through nums2: ");
		for (i=0;i<10;i++)
			System.out.print(nums1[i]+" ");
		System.out.println();
		
	}

}
