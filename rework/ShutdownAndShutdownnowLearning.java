package rework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//public class ShutdownAndShutdownnowLearning {
//	public static void main(String[] args) throws ExecutionException,InterruptedException{
//		SetPool setPool = new SetPool();
//		
////		setPool.addTasks(0, 10);
//
//		// use shutdown(): all tasks in queue will execute, but won't wait for them finishing executing.
////		System.out.println("call shutdown...");
////		setPool.shutdown();
////		System.out.println("Try to add task to pool 1 after call shutdown ...");
////		setPool.addTasks(110, 111);
//		
//
//		// use shutdownNow
////		System.out.println("call shutdownNow...");
////		List<Runnable> list = setPool.shutdownNow();
////		System.out.println(list.size());
//		
//		ArrayList<Future<Integer>> aList = setPool.submitTasks(0, 5);
////		System.out.println("call shutdown...");
////		setPool.shutdown();
//		System.out.println("call shutdownNow...");
//		List<Runnable> list = setPool.shutdownNow();
//		System.out.println(list.size());
//		for (int i = 0; i < 5; i++) {
//			System.out.println("Result of No."+i+" is:"+ aList.get(i).get());
//		}
//	}
//}
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
	
	public ArrayList<Future<Integer>> submitTasks(int start,int num){	
		ArrayList<Future<Integer>> arrayList = new ArrayList<Future<Integer>>();
		
		for(int i=start;i<num;i++){
			System.out.println("Submitting task No."+i);
			arrayList.add(threadPoolExecutor.submit(new CallTask(i)));
		}
		
		System.out.println("Finished. Submitted "+(num - start)+" tasks to pool 1 ...");
		System.out.println("Tasks in queue: "+threadPoolExecutor.getQueue().size());
		
		return arrayList;
	}
	
	// 
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
		System.out.println("Start a new thread...");
		Thread testJoin1 = new Thread(new TestJoin(),(Thread.currentThread().getName()+"_testJoin1"));
		Thread testJoin2 = new Thread(new TestJoin(),(Thread.currentThread().getName()+"_testJoin2"));
		testJoin1.start();
		try {
			testJoin1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testJoin2.start();
		
	}
}

class TestJoin implements Runnable {
	@Override
	public void run() {
		for(int i =0;i<2;i++){
			System.out.println(Thread.currentThread().getName()+": "+i);			
		}
	}
}

class CallTask implements Callable<Integer>{
	private int i;
	
	public CallTask(int m) {
		this.i = m;
	}
	
	@Override
	public Integer call() throws Exception {
		System.out.println("\n" + Thread.currentThread().getName() + " starts at: " + System.currentTimeMillis());
		try {
			Thread.sleep(2300);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + e);
//			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ends at: " + System.currentTimeMillis()+"\n");
		return ((int)(Math.random()*1000*i));
	}
}

