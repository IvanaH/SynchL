package rework;

import java.util.ArrayList;
import java.util.Random;

public class ProducerConsumerSample {

	public static void main(String[] args) {
		PC pc= new PC();
		ProducerDemo producerDemo = new ProducerDemo(pc);
		ConsumerDemo consumerDemo = new ConsumerDemo(pc);
		
		Thread thread1 = new Thread(producerDemo);
		Thread thread2 = new Thread(consumerDemo);
		
		thread1.start();
		thread2.start();
	}
}

class ProducerDemo implements Runnable{
	PC pc;
	
	public ProducerDemo(PC pc) {
		this.pc = pc; 
	}
	public void run() {
		while (true) {
			synchronized (pc) {
				try {
					pc.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			}
		}
	}
}

class ConsumerDemo implements Runnable{
	PC pc;
	
	public ConsumerDemo(PC pc) {
		this.pc = pc; 
	}
	public void run() {
		while (true) {
			synchronized (pc) {
				try {
					pc.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
	
		}
	}
}

class PC{
	ArrayList<Integer> list= new ArrayList<>();
	int capacity = 5;
	
	public void producer() throws InterruptedException{
		while (list.size()>=capacity) {
			System.out.println("list is full. Producer is waiting.");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		Thread.sleep(500);
		int value = new Random().nextInt(100);
		System.out.print("producer insert: " + value + "\n");
		list.add(value);
		notify();
	}
	
	public void consumer() throws InterruptedException {
		while (list.size()==0) {
				System.out.println("list is empty. Consumer is waiting.");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}		
		Thread.sleep(1000);
		int value = list.remove(0);
		System.out.print("consumer get: " + value + "\n");
		notify();
	}
}
