class Queue3{
	private char q[]; // the array which hold the queue
	private int putloc,getloc; // the put and get indices
	
	//Construct an empty Queue given its size
	Queue3(int size){
		q = new char[size];
		putloc = getloc = 0;
	}
	
	//Construct a Queue from a Queue
	Queue3(Queue3 ob){
		putloc = ob.putloc;
		getloc = ob.getloc;
		q = new char[ob.q.length];
		
		//copy the elements
		for (int i = getloc; i< putloc; i++){
			q[i] = ob.q[i];
		}			
	}
	
	//Construct a Queue with initial values
	Queue3(char a[]){
		putloc = 0;
		getloc = 0;
		q = new char[a.length];
		
		for(int i =0; i<a.length;i++)
			put(a[i]);
	}
	
	//put a character into the queue
	void put(char ch){
		if(putloc == q.length){
			System.out.println("- Queue is full.");
			return;
		}
		
		q[putloc]=ch;
		putloc++;
	}
	
	//get a character from the queue
	char get(){
		if(getloc == putloc){
			System.out.println("- Queue is full.");
			return (char)0;
		}
		
		return q[getloc++];
	}
}

//Demostrate the Queue Class
class QDemo3 {
	public static void main(String args[]){
		//construct 10 elements empty queue
		Queue3 q1 = new Queue3(10);
		
		char name[] = {'T','o','m'};
		//construct queue from array
		Queue3 q2 = new Queue3(name);
		
		char ch;
		int i;
		
		//put some characters into q1
		for(i=0;i<10;i++)
			q1.put((char)('A'+i));
		
		//construct queue from another queue
		Queue3 q3 = new Queue3(q1);
		
		//show the queue
		System.out.print("Contents of q1: ");
		for(i=0; i<10; i++){
			ch = q1.get();
			System.out.print(ch);
		}
		
		System.out.println("\n");
		
		System.out.print("Contents of q2: ");
		for(i=0; i<3; i++){
			ch = q2.get();
			System.out.print(ch);
		}
		
		System.out.println("\n");
		
		System.out.print("Contents of q3: ");
		for(i=0; i<10; i++){
			ch = q3.get();
			System.out.print(ch);
		}
				
	}
}
