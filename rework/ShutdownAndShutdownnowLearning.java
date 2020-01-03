package rework;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ShutdownAndShutdownnowLearning {
	public static void main(String[] args) {
		SetPool setPool = new SetPool();
		setPool.addTasks(0, 10);
		
		// use shutdown
//		System.out.println("call shutdown...");
//		setPool.shutdown();
//		System.out.println("Try to add task to pool 1 after call shutdown ...");
//		setPool.addTasks(110, 111);
		

		// use shutdownNow
		System.out.println("call shutdownNow...");
		List<Runnable> list = setPool.shutdownNow();
		System.out.println(list.size());
		
	}
}
class SetPool{
	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

	public void addTasks(int start,int num){		
		for(int i=start;i<num;i++){
			System.out.println("Adding task No."+i);
			threadPoolExecutor.execute(new SinTask(i));
		}
		
		System.out.println("Finished. Added "+(num - start)+" tasks to pool 1 ...");
		System.out.println("Tasks in queue: "+threadPoolExecutor.getQueue().size());
	}
	
	
	public void shutdown() {
		threadPoolExecutor.shutdown();
	}
	
	public List<Runnable> shutdownNow() {
		return threadPoolExecutor.shutdownNow();
	}
}


class SinTask implements Runnable{
	private int i;
	
	public SinTask(int m) {
		this.i = m;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + e);
//			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": No."+i+" task is running...");		
//		System.out.println("Start a new thread...");
//		Thread testJoin1 = new Thread(new TestJoin(),(Thread.currentThread().getName()+"_testJoin1"));
//		Thread testJoin2 = new Thread(new TestJoin(),(Thread.currentThread().getName()+"_testJoin2"));
//		testJoin1.start();
//		try {
//			testJoin1.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		testJoin2.start();
		
	}
}

class TestJoin implements Runnable {
	@Override
	public void run() {
		for(int i =0;i<5;i++){
			System.out.println(Thread.currentThread().getName()+": "+i);			
		}
	}
}

