package rework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeCostOfMultiThread {

	public static void main(String[] args) {
		TotalTimeCost totalTimeCost = new TotalTimeCost();
		totalTimeCost.getTotalTime(5);

	}
}

class TotalTimeCost{
	public void getTotalTime(int n) {
		long m_start_time = System.currentTimeMillis();
		ThreadPoolExecutor fixedExecutor = new ThreadPoolExecutor(n, n, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3));
		
		// to calculate the total cost time of this application,
		// the value is smaller than sum of cost time of each thread when in multiple thread scenario
		CountDownLatch sl = new CountDownLatch(n);
		
		// summation of cost time of each thread
		AtomicInteger totalTime = new AtomicInteger();
		
		try {
			for(int i=0;i<n;i++){
				Thread thread = new Thread(new UnitThread(sl,totalTime),Integer.toString(i));
				fixedExecutor.submit(thread);			
			}
			sl.await();
			long m_end_time = System.currentTimeMillis();
			System.out.println("Total cost of this application is: "+ (m_end_time-m_start_time)+ " ms");
			System.out.println("Total cost of all threads is: "+ totalTime + " ms");
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			fixedExecutor.shutdown();
		}
	}
}

class UnitThread implements Runnable{
	CountDownLatch cLatch;
	AtomicInteger t;
	
	public UnitThread(CountDownLatch cDL, AtomicInteger total) {
		cLatch = cDL;
		t = total;
	}
	
	@Override
	public void run(){
		try {
			long start_time = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName()+ " is running...");
			Thread.sleep((long) (Math.random() * 10000));
			long end_time = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName()+ " costs: "+ (end_time-start_time) + " ms");	
		    t.getAndAdd((int)(end_time-start_time));
			cLatch.countDown();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}