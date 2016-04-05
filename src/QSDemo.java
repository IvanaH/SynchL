class QuickSort{
	//set up a call to the actual Quicksort
	static void qsort(char items[]){
		qs(items,0,items.length - 1);
	}
	
	//A recursive version of Quicksort for charactors
	private static void qs(char items[],int left, int right){
		int i,j;
		char x,y;
		
		i = left; j=right;
		x = items[(left+right)/2];
		
		do{
			while((items[i]<x) && i<right)
				i++;
			while((items[j]>x) && j>left)
				j--;
			
			if(i<=j){
				y = items[i];
				items[i] = items[j];
				items[j] = y;
				i++;
				j--;
			}
		}while(i<=j);
		
		if(left < j) 
			qs(items,left,j);
		if(i<right) 
			qs(items,i,right);
	}	
}

class QSDemo {
	public static void main(String args[]){
		char a[] = {'d','x','c','a','l','r','p','i','j','n'};
		int i;
		
		System.out.println("Original array: ");
		for(i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
		
		//Sort the array
		QuickSort.qsort(a);
		
		System.out.println("Sorted array: ");
		for(i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
	}
}
