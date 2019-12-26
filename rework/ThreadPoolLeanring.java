package rework;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolLeanring{
	public static void main(String[] args) {
//		UseArrayBlockingQ useArrayBlockingQ = new UseArrayBlockingQ(2, 5, 30);
//		useArrayBlockingQ.produceAndConsume();
		
		ThreadPoolExecutorLearning threadPoolExecutorLearning = new ThreadPoolExecutorLearning(5,2);
		ThreadPoolExecutor threadPool = threadPoolExecutorLearning.FixedPool();
		ArrayBlockingQueue<Integer> tlist = new ArrayBlockingQueue<>(30);
 		Runnable producer1= new PCFactory().getPC("Producer",tlist);
 		Runnable consumer1 = new PCFactory().getPC("consumer", tlist);
 		Runnable consumer2 = new PCFactory().getPC("consumer", tlist);
 		threadPool.execute(producer1);
 		threadPool.execute(consumer1);
 		threadPool.execute(consumer2);
	} 
}


class PCFactory{
	public Runnable getPC(String type, ArrayBlockingQueue<Integer> tlist) {
		if (type.equalsIgnoreCase("producer")){
			return new PLeaningProducer(tlist);
		}else {
			return new PLeaningConsumer(tlist);
		}
	}
}

class ThreadPoolExecutorLearning{
	int taskNum=1;
	int threadNum=1;
	
	public ThreadPoolExecutorLearning(int taskNum,int threadNum) {
		this.taskNum = taskNum;
		this.threadNum = threadNum;
	}
	public ThreadPoolExecutor StaticPool() {
		ThreadPoolExecutor singleThreadTP = new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(taskNum));
		return singleThreadTP;
	} 
	public ThreadPoolExecutor FixedPool() {
		ThreadPoolExecutor fixedThreadTP = new ThreadPoolExecutor(threadNum,threadNum,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(taskNum));
		return fixedThreadTP;
	}
	public ThreadPoolExecutor CachedPool() {
		ThreadPoolExecutor CachedThreadTP = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(taskNum));
		return CachedThreadTP;
	}
}


class MyProducer implements Runnable {
	private ArrayBlockingQueue<Integer> tlist;
	private int e = 0;
	
	public MyProducer(ArrayBlockingQueue<Integer> tlist) {
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
//		System.out.println("Produce: "+this.e);
		try {
			tlist.put(e);
     		System.out.println(Thread.currentThread().getName()+" has put: "+e);
		} catch (InterruptedException  e2) {
			System.out.println(e2);
		}
		this.e++;
	}
}


class MyConsumer implements Runnable{
	private ArrayBlockingQueue<Integer> tlist;
	
	public MyConsumer(ArrayBlockingQueue<Integer> tlist) {
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
			System.out.println(Thread.currentThread().getName()+" has got: "+value);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}