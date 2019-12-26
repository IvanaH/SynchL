package rework;

public class FactoryAndPolymor {

	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		
		Shape trangle = shapeFactory.getShape("trangle");
		trangle.getSize();
		
		Shape square = shapeFactory.getShape("square");
		square.getSize();
		// can't call immediately
		((Square)square).getSuper();
		((Square)square).getSuper(1);
	}
}

interface Shape{
	public void getSize();
}

class Trangle implements Shape{
	public void getSize(){
		System.out.println("size of trangle: 1/2 * height * base");
	}
}

class Retangle implements Shape{
	public void getSize(){
		System.out.println("size of retangle: length * width");
	}
}

class Square extends Retangle{
	@Override
	public void getSize(){
		System.out.println("size of Square: length * length");
	}
	
	
	public void getSuper() {
		super.getSize();
	}

	public void getSuper(int i) {
		System.out.println("Overload getSuper() ... ");
	}
}

class ShapeFactory{
	public Shape getShape(String type) {
		if (type.equalsIgnoreCase("Trangle"))
			return new Trangle();
		else if (type.equalsIgnoreCase("Retangle"))
			return new Retangle();
		else if (type.equalsIgnoreCase("Square"))
			return new Square();
		
		return null;		
	}
}