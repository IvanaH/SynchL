//This class implements a "fail-soft" array which prevents runtime error.

class FSDemo {
	private int a[];
	private int errval; //value to return if get() fails
	public int length;
	
	public FSDemo(int size, int errv){
		a = new int[size];
		errval = errv;
		length = size;
	}
	
	//Return value at given index
	public int get(int index){
		if(indexOK(index)) 
			return a[index];
		return errval;
	}
	
	// put a value at an index. Return false on failure.
	public boolean put(int index, int val){
		if(indexOK(index)){
			a[index] = val;
			return true;
		}
		return false;
	}
	
	//Return true if index is within bounds
	public boolean indexOK(int index){
		if(index >=0 & index < length)
			return true;
		return false;
	}
}


//Demonstrate the fail-soft array
class FailSoftArray{
		public static void main (String args[]){
			FSDemo fs = new FSDemo(5,-1);
			int x;
			
			//show quiet failures
			System.out.println("Fail quietly.");
			for (int i =0; i<(fs.length *2);i++)
				fs.put(i, i*10);
			
			for (int i =0; i<(fs.length *2);i++){
				x = fs.get(i);
				
				if(x != -1)
					System.out.print(x + " ");
			}
			System.out.println("");
			
			//now, handle failures
			System.out.println("\nFail with error reports.");
			for(int i = 0 ; i<(fs.length *2);i++)
				if(!fs.put(i, i*10))
					System.out.println("Index "+i+" out-of-bonds.");
			
			for(int i = 0 ; i<(fs.length *2);i++){
				x = fs.get(i);
				if (x!= -1)
					System.out.print(x+ " ");
				else
					System.out.println("Index "+i+" out-of-bonds.");
			}				
		}
	}

