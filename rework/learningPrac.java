package rework;

// Generic class
class Box<T> {
	private T t;
	
	public void setter(T t){
		this.t = t;
	}
	
	public T getter(){
		return this.t;
	}
}

class learningPrac{
	int a,b,c,d;
	public void showData(){
		System.out.printf("a,b,c,d are: %d %d %d %d\n" , this.a, this.b, this.c, this.d);		
	}

	public void setData(int m, int n){
		a = m;
		b = n;
		System.out.printf("a and b are: %d %d \n" , a,b);		
		System.out.printf("this.a and this.b are: %d %d \n" , this.a, this.b);	
		System.out.println();	
		
	}
	
	public void setDataII(int c, int d){
		c = c;
		d = d;
		System.out.printf("c and d are: %d %d \n" ,c,d);		
		System.out.printf("this.c and this.d are: %d %d \n" , this.c, this.d);		
	}
	
	public void loopRelated(){
		Boolean flag = true;
		Boolean innerFlag = false;
		while(true){
			System.out.println("in while");
			if(flag){
				System.out.println("in while-if");
				if(innerFlag){
					System.out.println("in while-if-if");
					break;
				}
			}else{
				System.out.println("in while-else");
				continue;
			}
				
		}
	}

	public static void main(String args[]){
    	learningPrac test = new learningPrac();
//	    test.setData(1, 2);
//	    test.setDataII(3, 4);
//		Box<Integer> intBox = new Box<Integer>();
//		Box<String> strBox = new Box<String>();
//		intBox.setter(new Integer(1));
//		strBox.setter("String");
//		System.out.println(intBox.getter());
//		System.out.println(strBox.getter());	
		test.loopRelated();
    }
}





