package thinkingIJ;

class CastingAndRounding {
	public static void main(String[] args) {
		double above = 0.7, below = 0.4;
		float fabove = 0.7f, fbelow = 0.4f;
		
		System.out.println("(int)above: "+(int)above+"  (int)below:"+(int)below);
		System.out.println("(int)fabove: "+(int)fabove+"  (int)fbelow:"+(int)fbelow);
		
		System.out.println();
		
		System.out.println("Math.round(above): "+Math.round(above)+"  Math.round(below):"+Math.round(below));
		System.out.println("Math.round(fabove): "+Math.round(fabove)+"  Math.round(fbelow):"+Math.round(fbelow));
	}

}
