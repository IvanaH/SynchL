package rework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeCosyOfMultiThreadTest {
	
	private static final int n = 5;
	private static ThreadPoolExecutor fixedExecutor = null;
	private static CountDownLatch latch = null;
	private static final AtomicInteger totalTime = new AtomicInteger();
	
	@Before
	public void init() {
		fixedExecutor = new ThreadPoolExecutor(n, n, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3));
		latch = new CountDownLatch(n);
	}
	
	@Test
	public void testFunc2() {
		
		
		
	}
	
	@Test
	public void testTotalTime() {
		long m_start_time = System.currentTimeMillis();
		// to calculate the total cost time of this application,
		// the value is smaller than sum of cost time of each thread when in multiple thread scenario
		
		// summation of cost time of each thread
		
		try {
			for(int i=0;i<n;i++){
				fixedExecutor.execute(new UnitThread(latch,totalTime));
			}
			latch.await();
			long m_end_time = System.currentTimeMillis();
			System.out.println("Total cost of this application is: "+ (m_end_time-m_start_time)+ " ms");
			System.out.println("Total cost of all threads is: "+ totalTime + " ms");
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
	}
	
	@After
	public void destory() {
		if(fixedExecutor!=null) {
			fixedExecutor.shutdown();
		}
	}
	
}

//class UnitThread implements Runnable{
//	CountDownLatch cLatch;
//	AtomicInteger t;
//	
//	public UnitThread(CountDownLatch cDL, AtomicInteger total) {
//		cLatch = cDL;
//		t = total;
//	}
//	
//	@Override
//	public void run(){
//		try {
//			long start_time = System.currentTimeMillis();
//			System.out.println(Thread.currentThread().getName()+ " is running...");
//			Thread.sleep((long) (Math.random() * 10000));
//			long end_time = System.currentTimeMillis();
//			System.out.println(Thread.currentThread().getName()+ " costs: "+ (end_time-start_time) + " ms");	
//		    t.getAndAdd((int)(end_time-start_time));
//			cLatch.countDown();
//		} catch (InterruptedException e) {
//			System.out.println(e);
//		}
//	}
//}
