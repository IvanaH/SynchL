
class SDemo2 {
	public static void main(String args[]){
		System.out.println("StaticDemo.y : "+StaticDemo.y);
		
		StaticDemo.y = 786;
		System.out.println("Set StaticDemo.y to 786.");
		
		System.out.println("StaticDemo.y : "+StaticDemo.y);
	}

}
