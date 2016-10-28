package thinkingIJ;

class Polymo {
	void A(int a){
		int i = a;
		System.out.println(i);
	}
	
	void A(boolean b){
		boolean i = false;
		System.out.println(i);
	}
	
	public static void main(String[] args) {
		Polymo p = new Polymo();
		p.A(true);
		p.A(3);
	}

}
