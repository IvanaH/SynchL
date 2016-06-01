class Outer{
	int nums[];
	
	Outer(int n[]){
		nums = n;
	}
	
	class Inner{
		int min(){
			int m = nums[0];
			for(int i =1; i< nums.length;i++)
				if(nums[i]<m) 	m = nums[i];
			return m;
		}
		
		int max(){
			int m = nums[0];
			for(int i =1; i< nums.length;i++)
				if(nums[i]>m)	m = nums[i];			
			return m;
		}
		
		int avg(){
			int m = nums[0];			
			for(int i =1; i< nums.length;i++)
					m = nums[i] + m;			
			return m / nums.length;
		}
	}
	
	void analyze(){
		Inner inOb = new Inner();
		
		System.out.println("Minimum: "+ inOb.min());
		System.out.println("Maximum: "+ inOb.max());
		System.out.println("Average: "+ inOb.avg());
	}	
}

class NestedClassDemo {
	public static void main(String args[]){
		int x[] = {3,2,1,5,6,9,7,8};
		
		Outer outob = new Outer(x);
		
		outob.analyze();
//		System.out.println("Minimum of Outer: "+ outob.min());
	}
}
