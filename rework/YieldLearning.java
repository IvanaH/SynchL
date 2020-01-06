package rework;

public class YieldLearning {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new PThread(),"Thread - 1");
//		Thread thread2 = new Thread(new PThread(),"Thread - 2");
		Thread thread2 = new Thread(new CThread(),"Thread - 2");
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(Thread.MIN_PRIORITY);
		thread1.start();
		thread2.start();
	}
}

class PThread implements Runnable{
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()+" item: "+ i + "..."+System.currentTimeMillis());
			Thread.yield();			
		}		
	}
}

class CThread implements Runnable{
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()+" item: "+ i + "..."+System.currentTimeMillis());
			Thread.yield();			
		}		
	}
}
