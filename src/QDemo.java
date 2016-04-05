class Queue{
	char q[]; // the array which hold the queue
	int putloc,getloc;
	
	Queue(int size){
		q = new char[size];
		putloc = getloc = 0;
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
		char v;
		
		if(getloc == putloc){
			System.out.println("- Queue is full.");
			return (char)0;
		}
		
		v = q[getloc];
		getloc++;
		return v; 
	}
}

//Demostrate the Queue Class
class QDemo {
	public static void main(String args[]){
		Queue bigQ = new Queue(100);
		Queue smallQ = new Queue(4);
		char ch;
		int i;
		
		System.out.println("Using bigQ to store the alphabet.");
		
		//Put some numbers into bigQ
		for(i=0;i<26;i++)
			bigQ.put((char)('A'+i));
		
		//Retrieve and display elements from bigQ
		System.out.print("Content of bigQ: ");
		for (i=0;i<26;i++){
			ch = bigQ.get();
			if(ch!=(char)0)
				System.out.print(" "+ch);
		}
		
		System.out.println("\n");
		
		System.out.println("Using smallQ to generate errors.");
		for(i=0;i<5;i++){
			System.out.print("Attempting to store "+(char)('Z'-i));
			smallQ.put((char)('Z'-i));
			System.out.println();
		}
		System.out.println();
		
		//more error on smallQ
		System.out.print("Content of smallQ:");
		for(i=0;i<5;i++){
			ch = smallQ.get();
			if (ch != (char)0)
				System.out.print(" "+ch);
		}
	}
}
