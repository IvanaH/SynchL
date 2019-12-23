package rework;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolLeanring{
	public static void main(String[] args) {
		UseArrayBlockingQ useArrayBlockingQ = new UseArrayBlockingQ(2, 5, 30);
		useArrayBlockingQ.produceAndConsume();
	} 
}


class UseStaticPool{
	ThreadPoolExecutor threadPoolExecutor;
}

// use take() and put() to get and add the elements
class UseArrayBlockingQ{
	int producerNum, consumerNum;
	int queueLength;
	int totalTasks;
	ArrayList<Thread> threadList = new ArrayList<Thread>();
	ArrayBlockingQueue<Integer> tlist;
	
	public UseArrayBlockingQ(int pNum, int cNum, int tNum) {
		this.producerNum = pNum;
		this.consumerNum = cNum;
		this.queueLength = tNum;
	}
	
	public void produceAndConsume(){
		tlist = new ArrayBlockingQueue<Integer>(this.queueLength);	
		
		for(int i = 0;i<producerNum;i++){
			String pThreadName = "Producer-"+i;
			Thread pThread = new Thread(new PLeaningProducer(tlist),pThreadName);
			threadList.add(pThread);
			pThread.start();
		}
		
		for(int i = 0;i<consumerNum;i++){
			String cThreadName = "Consumer-"+i;
			Thread cThread = new Thread(new PLeaningConsumer(tlist),cThreadName);
			threadList.add(cThread);
			cThread.start();
		}
		
		//TODO: close all threads correctly 
//		while(true){
//			if (tlist.size() == 0){
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				if (tlist.size() == 0){
//					for(Thread pcthread : threadList){
//			     		System.out.println(pcthread.getState());
//						while (pcthread.getState() != Thread.State.WAITING){
//							continue;
//						}
//						pcthread.interrupt();
//					}
//					break;
//				}
//			}else
//				continue;
//		}
		
		System.out.println("All done! ");
	}
}

class PLeaningProducer implements Runnable {
	private ArrayBlockingQueue<Integer> tlist;
	private int e = 0;
	
	public PLeaningProducer(ArrayBlockingQueue<Integer> tlist) {
		this.tlist = tlist;
	}
	
	@Override
	public void run() {
		while(e<1000){
			producer();
		}
	}
	
	//TODO: How to let the limit work
	public void producer(){
//		int e =  new Random().nextInt(1000);
		System.out.println("Produce: "+this.e);
		try {
			tlist.put(e);
     		System.out.println(Thread.currentThread().getName()+" has put: "+e);
		} catch (InterruptedException  e2) {
			System.out.println(e2);
		}
		this.e++;
	}
}


class PLeaningConsumer implements Runnable{
	private ArrayBlockingQueue<Integer> tlist;
	
	public PLeaningConsumer(ArrayBlockingQueue<Integer> tlist) {
		this.tlist = tlist;
	}
	
	@Override
	public void run() {
		while (true) {
			consumer();	   //not need to add sleep() here or in run() of producer to avoid deadlock
		}
	}
	
	public void consumer(){
		try {
		    Integer value = tlist.take();
			System.out.println(Thread.currentThread().getName()+"has got: "+value);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}