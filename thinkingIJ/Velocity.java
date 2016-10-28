package thinkingIJ;

import java.util.Random;
//import java.text.DecimalFormat;

class Velocity {
	public static void main(String[] args) {
		double v;
		double s,t;
		
		
		RandomCon rc = new RandomCon();
		s = rc.getRandom(100);
		t = rc.getRandom();
		v = s/t;
		
		System.out.println("The velocity is: "+v);
	}

}

class RandomCon{
	Random rand = new Random(42);
	
	int getRandom(){
		return rand.nextInt();	
	}
	
	int getRandom(int x){
		return rand.nextInt(x);	
	}
}