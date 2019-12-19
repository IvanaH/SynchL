package rework;

import java.util.ArrayList;
import java.util.List;


public class ProducerMultiConsumers {
	public static void main(String[] args) {
		TaskList taskList = new TaskList();
		
		Thread ProducerT = new Thread(new Producer(300,taskList),"Producer");
		ProducerT.start();
//		Thread ConsumerT = new Thread(new Consumer(taskList));
//		ConsumerT.start();

		ThreadPoolManually threadPoolManually = new ThreadPoolManually(5, taskList);	
		threadPoolManually.ConsumerPool();
//		
//		while (ProducerT.interrupted()) {
//			threadPoolManually.shutDown();	
//			System.out.println("Shut down all consumers.");
//		}
	}
}

class ThreadPoolManually{
	private int MAX_NUM = 10;
	private int workNum; 
	TaskList tList;
	ArrayList<Thread> Consumers = new ArrayList<Thread>(); 
	
	//set concurrency MAX_NUM
	public ThreadPoolManually(int num, TaskList taskList) {
		if (num < MAX_NUM)
			this.workNum = num;
		else
			this.workNum = MAX_NUM;
		this.tList = taskList;
	}
	
	//initialize thread pool then start each thread
	public void ConsumerPool(){
		for (int i=0;i<this.workNum;i++){
			String threadName = "Consumer-"+i;
			Thread thread = new Thread((new Consumer(tList)),threadName);
			Consumers.add(thread);
			thread.start();
		}
	}
	
	public void shutDown() {
		for(Thread Consumer : Consumers){
			Consumer.interrupt();
		}
		
	}
}

class Producer implements Runnable {
	private int counter = 0;
	private int total;
	TaskList tlist;
	
	// set the tasklist, and the total number of tasks 
	public Producer(int total,TaskList tasklist) {
		this.tlist = tasklist;
		this.total = total;
	}
 	
	@Override
	public void run() {
		while (counter<=total) {
			System.out.print("\nAsk producer...\n");
			producer();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public  void producer(){
		int value = counter;
		try {
			tlist.addTask(value);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		counter ++;
	}
}


class Consumer implements Runnable{
	TaskList tlist;
	
	public Consumer(TaskList tasklist) {
		this.tlist = tasklist;
	}
	
	@Override
	public void run() {
		while (true) {
			consumer();							
		}		
	}
	
	public void consumer(){
	    try {
			tlist.removeTask();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class TaskList{
	private List<Object> taskList = new ArrayList<>();
	private int CAPACITY = 2;	

	//set the capacity of taskList
	public void setCapacity(int num) {
		this.CAPACITY = num;
	}
	
	//get the length of taskList
	public int getLength() {
		return taskList.size();
	}
	
	//add a task
	public <T> void addTask(T t)  throws InterruptedException{
//		boolean getLock = false;
		synchronized (taskList) {
//			getLock = true;
			while (taskList.size() >= this.CAPACITY) {
				System.out.println("Full tasklist. "+Thread.currentThread().getName()+" is waiting...\n");
				taskList.wait();	
				System.out.println(Thread.currentThread().getName()+" wakes up...\n");
			}
			taskList.add(t);
			System.out.print("Add value: "+t+"\n");
			taskList.notify();
		}
//		if (!getLock)
//			System.out.println(" Producer DO NOT get lock. \n");
	}
	
	//remove a task
	public Object removeTask() throws InterruptedException {
		synchronized (taskList) {
			Object tObject=null;
			while (taskList.size() == 0){
				System.out.println("Empty tasklist. "+Thread.currentThread().getName()+" is waiting...\n");
				taskList.wait();
//				System.out.println(Thread.currentThread().getName()+" wakes up...\n");
			}
			Thread.sleep(1000);
			tObject = taskList.remove(0);
			System.out.println(Thread.currentThread().getName()+" get value: "+tObject);
			taskList.notify();
			return tObject;
		}
	}
}
