class ForEach2 {
	public static void main(String args[]){
		int sum = 0;
		int nums[][] = new int[5][7];
		
		//give nums some values
		for(int i = 0;i<5;i++)
			for (int j = 0;j<7;j++)
				nums[i][j] = (i+1)*(j+1);
		
		//Display and sum the values
		for(int x[]:nums){
			for(int i:x){
				System.out.println("Valus is "+i);
				sum = sum + i;
				i = i+1;
			}
		}
		System.out.println();

		for(int x[]:nums){
			for(int i:x)
				System.out.print(i+" ");
			System.out.println();
		}
		System.out.println();

		System.out.println("Summation: "+sum);
	}

}
