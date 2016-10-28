package thinkingIJ;

class DogDemo {
	public static void main(String[] args) {
		Dog d1 = new Dog("spot", "Ruff!");
		Dog d2 = new Dog("scruffy", "Wurf!");
		Dog d3 = d1;
		
		d1.printInfo();
		d2.printInfo();
		
		System.out.println();
		
		System.out.println("d1 == d3? "+(d1 == d3));
		System.out.println("d1 equals d3? "+(d1.equals(d3)));

		System.out.println();

		d3 = new Dog("spot", "Ruff!");
		System.out.println("d1 == d3? "+(d1 == d3));
		System.out.println("d1 equals d3? "+(d1.equals(d3)));

	}

}

class Dog{
	private String name;
	private String says;
	
	Dog(String n, String s){
		name = n;
		says = s;
	}
	
	void printInfo(){
		System.out.println("The name is "+name+"; the says is "+says);
	}
}
