class SumArgs{
	int sum(int ... v){
		int s = 0;
		
		System.out.println("Content of sum(int ...) is:");
		for(int i = 0; i<v.length;i++){
			System.out.println(" args "+i+" : "+v[i]);
			s = s+v[i];
		}			
		return s;
	}
}

class Stack{
	private char stck[];
	private int tops;
	
	Stack(int n){
		stck = new char[n];
		tops = 0;
	}
	
	Stack(Stack ob){
		tops = ob.tops;
		stck = new char[ob.stck.length];
	}
	
	Stack(char a[]){
		tops = 0;
		stck = new char[a.length];
		for(int i=0; i<a.length;i++)
			push(a[i]);
	}
	
	//Push characters onto the stack
	void push(char ch){
		if(tops == stck.length)
			System.out.println(" -- Stack is full.");
			return;
	}
	
	//Pop a character from the stack
	char pop(){
		if (tops == 0){
			System.out.println(" -- Stack is empty.");
			return (char) 0;
		}
		
		tops --;
		return stck[tops];
	}
}

abstract class TwoDShape{
	private double w;
	private double h;
	private String name;
	
	TwoDShape(){
		w = h = 0.0;
		name = "none";
	}
	
	TwoDShape(double a, double b, String c){
		w = a;
		h = b;
		name = c;		
	}
	
	TwoDShape(TwoDShape ob){
		w = ob.w;
		h = ob.h;
		name = ob.name;
	}
	
	void setW(double w1){ w = w1; }
	void setH(double h1){ h = h1; }
	double getW(){ return w; }
	double getH(){ return h; }
	
	void showDim(){
		System.out.println("Width and height is "+w+" and "+h);
	}
	
	abstract double area();
}

class circle extends TwoDShape{
	private String color;
		
	circle(){
		super();
		color = "none";		
	}
	
	circle(double a, String b, String c){
		super(a,a,b);
		color = c;
	}
	
	circle(circle ob){
		super(ob);
		color = ob.color;
	}
	
	void setC(String a){ color = a; }
	String getC(){ return color;}
	
	double area(){
		return 3.14*(getW()/2)*(getW()/2);
	}
	
	void showDim(){
		System.out.println("Radius of this circle is "+ getW()/2);
		System.out.println("And it color is "+ getC());
	}
}

class doExercise {
	public static void main(String args[]){
		SumArgs obsix = new SumArgs();
		System.out.println("Sum of the varargs is: "+ obsix.sum(2,4,6));
		System.out.println();
		System.out.println("Sum of the varargs is: "+ obsix.sum(1,3,45,7,80));		
		System.out.println();
		
		TwoDShape obseven[] = new TwoDShape[3];
		
//		obseven[0] = new TwoDSahpe();   //TwoDSahpe is an abstract class, 
//		                                //cannot be resolved to a type
		
		circle obc1 = new circle();
		obseven[0] = new circle();
		obseven[1] = new circle(15.0, "QA", "Amarilla");
		
		obc1.setW(12.0);
		obc1.setC("Rejo");
		obc1.showDim();
		System.out.println("And the area is: "+obc1.area());
		System.out.println();
		
		obseven[0].showDim();
		System.out.println("And the area is: "+obseven[0].area());
		System.out.println();
		
		obseven[1].showDim();
		System.out.println("And the area is: "+obseven[1].area());
		System.out.println();

		obseven[2] = new circle(obc1);
		circle obc2 = new circle(obc1);
		
//		obseven[2].getC;  // method of subclass can not be called by super class
		obseven[2].showDim();
		System.out.println("And the area is: "+ obseven[2].area());
		System.out.println();
		
		obc2.getC();
		obc2.showDim();
		System.out.print("And the area is: "+obc2.area());		
		System.out.println();

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(3.14);
		System.out.println(3.14*6);
		System.out.println(3.14*6*6);
	}
}
