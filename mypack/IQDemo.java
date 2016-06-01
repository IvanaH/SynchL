package mypack;

class FixedQ implements ICharQ{
	private char q[];
	private int putloc, getloc;
	
	FixedQ(int size){
		q = new char[size];
		putloc = getloc = 0;
	}
	
	//put a character into the queue
	public void put(char ch){
		if(putloc == q.length){
			System.out.println(" - Queue is full.");
			return;
		}		
		q[putloc] = ch;
		putloc ++ ;
	}
	
	//get a character from the queue
	public char get(){
		if(getloc == putloc){
			System.out.println(" - Queue is empty.");
			return (char)0;
		}
		getloc ++;
		return q[getloc-1];
	}
}

class CircularQ implements ICharQ{
	private char q[];
	private int putloc, getloc;
	
	CircularQ(int size){
		q = new char[size+1];
		putloc = getloc = 0;
	}
	
	//put a character into the queue
	public void put(char ch){
	//Circular queue, is full either putloc is one less than getloc, or if putloc is at the end 
	//and getloc is at the beginning
		if((putloc+1 == getloc) | (putloc == q.length) & (getloc == 0)){
			System.out.println(" - Queue is full.");
			return;
		}
		q[putloc] = ch;
		putloc ++;	
		if(putloc == q.length) putloc = 0;
	}
	
	public char get(){
		if(getloc == putloc){
			System.out.println(" - Queue is empty.");
			return (char)0;
		}
		char ch = q[getloc];
		getloc ++;
		if(getloc == q.length) getloc = 0;
		return ch;
	}	
}

class DynQ implements ICharQ{
	private char q[];
	private int putloc, getloc;
	
	DynQ(int size){
		q = new char[size];
		putloc = getloc = 0;
	}
	
	//put a character into the queue
	public void put(char ch){
		if(putloc == q.length){
			//increase the size
			char t[] = new char[q.length*2];
			//copy the elements into new queue
			for(int i=0; i<q.length;i++)
				t[i] = q[i];
			q = t;
		}		
		q[putloc] = ch;
		putloc ++ ;
	}
	
	//get a character from the queue
	public char get(){
		if(getloc == putloc){
			System.out.println(" - Queue is empty.");
			return (char)0;
		}
		getloc ++;
		return q[getloc-1];
	}	
}

class IQDemo {
	public static void main(String args[]){
		FixedQ q1 = new FixedQ(10);
		DynQ q2 = new DynQ(5);
		CircularQ q3 = new CircularQ(10);
		
		ICharQ iQ;
		
		char ch;
		int i;
		
		iQ = q1;
		for(i=0;i<10;i++)
			iQ.put((char)('A'+i));
		System.out.print("Contents of fixed queue: ");
		for(i=0;i<10;i++){
			ch = iQ.get();
			System.out.print(ch);
		}
		System.out.println();
		
		iQ = q2;
		for(i=0;i<10;i++)
			iQ.put((char)('Z'-i));
		System.out.print("Contents of dynamic queue: ");
		for(i=0;i<10;i++){
			ch = iQ.get();
			System.out.print(ch);
		}
		System.out.println();
		
		iQ = q3;
		for(i=0;i<10;i++)
			iQ.put((char)('A'+i));
		System.out.print("Contents of circular queue: ");
		for(i=0;i<10;i++){
			ch = iQ.get();
			System.out.print(ch);
		}
		System.out.println();
		
		//put more into circular queue
		for(i=10;i<20;i++)
			iQ.put((char)('A'+i));
		//show the queue
		System.out.print("Contents of circular queue: ");
		for(i=0;i<10;i++){
			ch = iQ.get();
			System.out.print(ch);
		}
		
		System.out.println("\nStore and consume from circular queue:");
		for(i=0;i<20;i++){
			iQ.put((char)('A'+i));
			ch = iQ.get();
			System.out.print(ch);
		}
	}
}
