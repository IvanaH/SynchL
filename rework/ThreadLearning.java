package rework;

// create thread by extend Thread Class
class extThr extends Thread{
	public void run() {
		System.out.println("Thread in extThr is running...");
	}
}

//create thread by implement runable interface
class impRunable implements Runnable{
	private Thread t;
	private String threadName;
	
	public impRunable(String thrName) {
		threadName = thrName;
	}
	
	public void run() {
		System.out.println("impRunable is running: "+threadName);	
		try {
			for (int i = 3;i>0;i--){
				System.out.println("impRunable Thread "+threadName+", "+i);
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			System.out.println("impRunable Thread "+threadName+" is interrupted.");	
		}
		System.out.println("impRunable Thread "+threadName+" exiting.");	
	}
	
	public void setT(Thread thr) {
		t = thr;		
	}
	
	public void start() {
		System.out.println("Start: "+threadName);	
		if (t==null){
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

//learning Synchronization
class printDemo{
	public void printS(String thrName) {
		for(int i=1;i<5;i++){
			System.out.print(thrName + " - "+i+"\n");
		}		
	}
}

class SynchLearning extends Thread{
	printDemo pDemo;
	private String thrName;
	
	public SynchLearning(String threadName,printDemo printDemo) {
		thrName = threadName;
		pDemo = printDemo;
	}

	//without synchronized
	public void run() {
		System.out.println(thrName+" is runing:");
		pDemo.printS(thrName);
	}
	
	//with synchronized
//	public void run() {
//		System.out.println(thrName+" is runing:");
//		synchronized(pDemo){
//			pDemo.printS(thrName);
//		}
//	}
	
	public void start(){
		Thread t = new Thread(this);
		t.start();
	}	
}


class ThreadLearning{
	public static void main(String[] args) {
		//access the same resource by multiple threads
		printDemo pDemo = new printDemo();
		
		SynchLearning sLearning1 = new SynchLearning("Thread1",pDemo);
		sLearning1.start();
		SynchLearning sLearning2 = new SynchLearning("Thread2", pDemo);
		sLearning2.start();		
////		extThr eThr = new extThr();
////		eThr.start();
//		
//		impRunable R1 = new impRunable("Thread-1");
////		R1.setT(new Thread(R1));
//		R1.start();
//		
//		impRunable R2 = new impRunable("Thread-2");
//		R2.start();
//		
////		impRunable R3 = new impRunable("Thread-3");
////		Thread thr = new Thread(R3);
////		thr.start();
	}
}

