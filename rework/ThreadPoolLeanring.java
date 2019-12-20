package rework;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
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

class UseArrayBlockingQ{
	int producerNum, consumerNum;
	int queueLength;
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
			pThread.start();
		}
		
		for(int i = 0;i<consumerNum;i++){
			String cThreadName = "Consumer-"+i;
			Thread cThread = new Thread(new PLeaningConsumer(tlist),cThreadName);
			cThread.start();
		}		
	}
}

class PLeaningProducer implements Runnable {
	private ArrayBlockingQueue<Integer> tlist;
	
	public PLeaningProducer(ArrayBlockingQueue<Integer> tlist) {
		this.tlist = tlist;
	}
	
	@Override
	public void run() {
		while(true){
			producer();
		}
	}
	
	public void producer(){
		int e =  new Random().nextInt();
		System.out.println("Produce: "+e);
		try {
			tlist.add(e);
     		System.out.println(Thread.currentThread().getName()+" has put: "+e);
		} catch (IllegalStateException  e2) {
			System.out.println(" List is full. ");
		}
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
			try {
				consumer();	
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void consumer(){
		try {
		    Integer value = tlist.poll();
			System.out.println(Thread.currentThread().getName()+"has got: "+value);
		} catch (Exception e) {
			System.out.println(" List is empty. ");
		}
	}

}