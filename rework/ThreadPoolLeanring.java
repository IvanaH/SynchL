package rework;

import java.util.concurrent.*;

public class ThreadPoolLeanring{
	public static void main(String[] args) {
		//create workQueue
		ArrayBlockingQueue<Object> tlist = new ArrayBlockingQueue<>(30);
		
		// create single-thread thread pool
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
//		// create fixed thread pool
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
//		// create cached thread pool
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,Integer.MAX_VALUE,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
		
		PCFactory pcFactory = new PCFactory();
		Runnable myProducer1 = pcFactory.getPC("producer",tlist);
		Runnable myConsumer1 = pcFactory.getPC("Consumer",tlist);
		Runnable myConsumer2 = pcFactory.getPC("Consumer",tlist);
		
		threadPoolExecutor.execute(myProducer1);
		threadPoolExecutor.execute(myConsumer1);
		threadPoolExecutor.execute(myConsumer2);
	}
}


class PCFactory{
	public Runnable getPC(String type, ArrayBlockingQueue<Object> tlist) {
		if (type.equalsIgnoreCase("producer")){
			return new MyProducer(tlist);
		}else {
			return new MyConsumer(tlist);
		}
	}
}


class MyProducer implements Runnable {
	private ArrayBlockingQueue<Object> tlist;
	private int e = 0;
	
	public MyProducer(ArrayBlockingQueue<Object> tlist) {
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
	private ArrayBlockingQueue<Object> tlist;
	
	public MyConsumer(ArrayBlockingQueue<Object> tlist) {
		this.tlist = tlist;
	}
	
	@Override
	public void run() {
		while (true) {
			consumer();	 
		}
	}
	
	public void consumer(){
		try {
			Object value = tlist.take();
			System.out.println(Thread.currentThread().getName()+" has got: "+value);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}