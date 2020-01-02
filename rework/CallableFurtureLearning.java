package rework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableFurtureLearning {
	public static void main(String[] args) {
		ChooseFurture chooseFurture1 = new ChooseFurture();
		chooseFurture1.useFurture();
		
		ChooseFurture chooseFurture2 = new ChooseFurture();
		chooseFurture2.useFurtureTask();
	}
}

class ChooseFurture{
	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3));
	Task task = new Task();
	
	public void useFurture() {
		System.out.println("Main thread executing....");
		Future<Integer> result = threadPoolExecutor.submit(task);
		threadPoolExecutor.shutdown();
		
		try {
			System.out.println("The result of task: "+result.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done!\n");		
	}
	
	public void useFurtureTask() {
		System.out.println("Main thread executing....");
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		threadPoolExecutor.submit(futureTask);
		threadPoolExecutor.shutdown();
		
		try {
			System.out.println("The result of task: "+futureTask.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done!\n");	
	}
}

class Task implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " start working....");
//		Thread.sleep((int)(Math.random()*1000));
		Thread.sleep(1200);
		int r = 0;
		r = (int)(Math.random()*100);
		return r;
	}
}
