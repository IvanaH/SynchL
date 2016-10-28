package thinkingIJ;

class WhileTest {
	static boolean condition(){
		boolean result = Math.random() < 0.9;
		System.out.print(result+", ");
		return result;
	}
	
	public static void main(String[] args) {
		while(condition())
			System.out.println("Inside the while.");
		System.out.println("Outside the while");
	}
}
