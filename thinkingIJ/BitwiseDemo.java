package thinkingIJ;

class BitwiseDemo {
	public static void main(String[] args) {
		int i1 = 0x2AAA;
		int i2 = 0x1555;
		
		System.out.println("i1(" + i1+"): "+Integer.toBinaryString(i1));
		System.out.println("i2(" + i2+"):   "+Integer.toBinaryString(i2));
		
		System.out.println();
		
		System.out.println("i1 AND i2: " + Integer.toBinaryString(i1&i2));
		System.out.println("i1 OR i2: " + Integer.toBinaryString(i1|i2));
		System.out.println("i1 XOR i2: " + Integer.toBinaryString(i1^i2));
		System.out.println("Not i1: " + Integer.toBinaryString(~i1));
		System.out.println("Not i2: " + Integer.toBinaryString(~i2));
	}
}
