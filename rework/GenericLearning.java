package rework;

// generic is for template of work flow, which is without parameter's Type
class GenericSample{
	//generic method print array elements
	public <T> void printEle(T[] inArray){
		for(T ele: inArray)
			System.out.printf("%s ", ele);
		System.out.println();
	}
}

//generic class
class GenericClass<T>{
	private T t;
	
	public GenericClass(T t) {
		this.t = t;
	}
	
	public T get(){
		return t;
	}
}

class GenericLearning{
	public static void main(String[] args) {
		Integer[] intArr = {1,4,8,12};
		Double[] douArr = {1.0, 3.13, 4.21};
		String[] strArr = {"this","that","those","These"};
		
		GenericSample gSample = new GenericSample();
		gSample.printEle(intArr);
		gSample.printEle(douArr);
		gSample.printEle(strArr);
		
		GenericClass gClass1 = new GenericClass(new String("This is String"));
		System.out.println(gClass1.get());
		GenericClass gClass2 = new GenericClass(new Integer(100));
		System.out.println(gClass2.get());
	}
}