package rework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchLearning {
	public static void main(String[] args) {
		Contest contest = new Contest(10);
		contest.getResult();
	}
}

class Contest implements Runnable{	
	private int particNum;
	CountDownLatch start = new CountDownLatch(1);
	CountDownLatch end;
	final ThreadPoolExecutor fixedThreadPoolExecutor;

	public Contest(int n) {
		System.out.println("Constructing .....");
		particNum = n;
        end = new CountDownLatch(n);
		fixedThreadPoolExecutor = new ThreadPoolExecutor(n,n,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
	}
	
	public void getResult() {
		try {
			System.out.println("Game starts.");
			for(int i=0;i<particNum;i++){
				Thread thread = new Thread(this, Integer.toString(i));
				fixedThreadPoolExecutor.submit(thread);
			}
			start.countDown();
			end.await();
			System.out.println("Game ends.");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			fixedThreadPoolExecutor.shutdown();		
		}
	}
	
	@Override
	public void run(){
		try {
			start.await();
			Thread.sleep((long) (Math.random() * 10000)); 
			System.out.println("No."+Thread.currentThread().getName()+" arrived.");
			end.countDown();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}
