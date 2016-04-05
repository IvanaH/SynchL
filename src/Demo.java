class Demo {
	static int num = 0;
	
	int m1(){
		num = 1;
		return num;
	}
	
	int m2(){
		return num;
	}
	
	int m3(){
		num = 3;
		return num;
	}

	public static void main (String args[])
	{
		System.out.println("num is "+ num);
		Demo D1 = new Demo();
		System.out.println("m1 is "+ D1.m1());
		System.out.println("num is "+ num);
		System.out.println("m2 is "+ D1.m2());	
		System.out.println("num is "+ num);
		System.out.println("m3 is "+ D1.m3());	
		System.out.println("num is "+ num);
	}
}
