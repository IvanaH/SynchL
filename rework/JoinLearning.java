package rework;

public class JoinLearning {
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new MyRunnable(), "Thread-1");
		Thread thread2 = new Thread(new MyRunnable(), "Thread-2");
		Thread thread3 = new Thread(new MyRunnable(), "Thread-3");
		Thread thread4 = new Thread(new MyRunnable(), "Thread-4");

		thread1.start();
		// start thread2 after thread1 died
		thread1.join();
		
		thread2.start();
		// start new thread after thread2 died or waited for 1.2 s
		thread2.join(1200);
		
		thread3.start();
		thread4.start();
		
		// end the main thread after all threads died
//		thread1.join();
//		thread2.join();
//		thread3.join();
//		thread4.join();		
		System.out.println(System.currentTimeMillis()+"::: Main thread ended...\n");
	}
}


class MyRunnable implements Runnable{
	@Override
	public void run() {
		System.out.println(System.currentTimeMillis()+ "::: Started::: "+Thread.currentThread().getName());
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ended::: "+Thread.currentThread().getName()+"\n");
	}
}