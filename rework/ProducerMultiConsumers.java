package rework;

import java.util.ArrayList;
import java.util.List;

public class ProducerMultiConsumers {
	public static void main(String[] args) {
		TaskList taskList = new TaskList();
		
		Thread ProducerT = new Thread(new Producer(taskList));
		Thread ConsumerT = new Thread(new Consumer(taskList));
		
		ProducerT.start();
		ConsumerT.start();	
	}

}

class Producer implements Runnable {
	private int counter = 0;
	TaskList tlist;
	
	public Producer(TaskList tasklist) {
		this.tlist = tasklist;
	}
 	
	@Override
	public void run() {
		while (true) {
			synchronized (tlist) {
				producer();
			}
		}
	}
	
	public  void producer(){
		int value = counter++;
		if(!tlist.addTask(value)){
			try{
				tlist.wait();
				System.out.println("List is full. Producer is waiting.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}else{
			tlist.notify();
		}
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
			synchronized (tlist) {
				consumer();							
			}
		}		
	}
	
	public void consumer(){
		if (tlist.getLength()==0) {
			try {
				tlist.wait();
				System.out.print("Empty tasklist. Consumer is waiting...\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.print("Consumer running, get value: "+tlist.removeTask()+"\n");
			tlist.notify();
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
	public <T> boolean addTask(T t) {
		synchronized (this.taskList) {
			if(taskList.size() < this.CAPACITY){
				taskList.add(t);
				System.out.print("Add value: "+t+"\n");
				return true;
			}else
				return false;			
		}
	}
	
	//remove a task
	public Object removeTask() {
		Object tObject=null;
		synchronized (this.taskList) {
			if(taskList.size() != 0)
				tObject = taskList.remove(0);
			return tObject;
		}
	}
}