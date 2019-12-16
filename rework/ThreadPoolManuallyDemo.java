package rework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ThreadPoolManuallyDemo<Job extends Runnable>{

    private static final int MAX_WORKER_NUMBERS=30;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;

    private final LinkedList<Job> jobs = new LinkedList<Job>();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private int workerNum;
//    private AtomicLong threadNum = new AtomicLong();
    
    //initialize thread pool
    public ThreadPoolManuallyDemo(int num){
    	if (num > DEFAULT_WORKER_NUMBERS){
    		this.workerNum = DEFAULT_WORKER_NUMBERS;
    	}else
    		this.workerNum = num;    
    	initializeWorkers(workerNum);
    }
    
    private void initializeWorkers(int num) {
    	for(int i=0;i<num;i++){
    		Worker worker = new Worker();
    		workers.add(worker);
    		//start the work thread
    		Thread thread  = new Thread(worker);
    		thread.start();    		
    	}
    }

    public void execute(Job job){
    	if(job !=null)
    		synchronized (jobs) {
    			jobs.add(job);
    			jobs.notify();				
			}
    }

    public void shutdown(){
    	for(Worker worker:workers){
    		worker.shutdown();
    	}
    }
    
    //add worker's number
    void addWorkers(int num){}
    
    //decrease worker's number
    public void removeWorker(int num){};

    public int getJobSize(){
    	return workers.size();
    }
}

class Worker implements Runnable{
	private boolean isRunning = true;
	
	public void run() {
		while (isRunning) {
			Job job = null;
			
			synchronized (jobs) {
				if(jobs.isEmpty()){
					
				}
				job = jobs.remove(0);
			}
			if (job != null)
				job.run();			
		}
	}
	
	public void shutdown() {
		isRunning = false;
	}
}

class Job implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名称:"+Thread.currentThread().getName()+";"+"job被指执行了");
    }
}