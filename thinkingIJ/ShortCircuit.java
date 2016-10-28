package thinkingIJ;

class ShortCircuit {
	static boolean test1(int i){
		System.out.print("test1("+i+"): ");
		System.out.println(i<1);
		return (i<1);
	}

	static boolean test2(int i){
		System.out.print("test2("+i+"): ");
		System.out.println(i<2);
		return (i<2);
	}
	static boolean test3(int i){
		System.out.print("test3("+i+"): ");
		System.out.println(i<3);
		return (i<3);
	}
	
	public static void main(String[] args) {
		System.out.println("test1(0)&&test2(3)&&test3(2): "+(test1(0)&&test2(3)&&test3(2)));
		System.out.println("test1(0)||test2(3)||test3(2): "+(test1(0)&&test2(3)&&test3(2)));
		System.out.println("test1(0)|test2(3)|test3(2): "+(test1(0)&&test2(3)&&test3(2)));
	}

}
