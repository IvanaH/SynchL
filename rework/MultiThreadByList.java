package rework;

import java.util.ArrayList;
import java.util.List;


/* Using class in MultiThreadReq.java
 *  @class
 *  sendReq: send request
 *  
 *  @class
 *  singleThread: runnable implementation
 */
class ThreadPoolByList{
	private int total;
	private int concurrency;
	ArrayList<Runnable> joblist = new ArrayList<>();
	List<Thread> workers = new ArrayList<>();	
	
	// by default, the total and concurrency is 1.
	public ThreadPoolByList() {
		total = 1;
		concurrency = 1;
	}
	
	public ThreadPoolByList(int totalThreads, int concurrntNum) {
		total = totalThreads;
		if (concurrntNum < 10)
			concurrency = concurrntNum;
		else
			concurrency = 10;
	}
	
	public void intialJobList(){
		for(int i=0;i<total;i++){
			//TODO add a new job to joblist
		}
	}

	//create a thread pool by list
	public void intializeWorkers(){
		for(int i=0;i<concurrency;i++){
			Thread t = new Thread();
			workers.add(t);	
			t.start();
		}
	}
	
	//get a available thread to execute job
	public void getWorker(ArrayList<Runnable> joblist) {
		Boolean isRunning = true;
		Runnable runnable = null;
		
		ArrayList<Runnable> jobList = joblist;
		
		while(isRunning){
			synchronized (jobList) {
				if (jobList.isEmpty())
					   isRunning = false;
					else {
					   runnable = jobList.remove(0);
					}
			}
			
			if (runnable!=null)
				runnable.run();
		}		
	}
	
	//add job to list
	
	//remove job from list
	
	//destroy threads when joblist is null
	

}

// get a available thread to execute job 
class workers implements Runnable{
	private Boolean running = true;
	
	public void run() {
		while(running){
			
		}		
	}
	
}



public class MultiThreadByList{
	public static void main(String[] args) {

	}
}