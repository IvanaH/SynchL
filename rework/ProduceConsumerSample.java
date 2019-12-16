package rework;

import java.util.ArrayList;
import java.util.Random;

public class ProduceConsumerSample {

	public static void main(String[] args) {
		PC pc= new PC();
		ProducerDemo producerDemo = new ProducerDemo(pc);
		ConsumerDemo consumerDemo = new ConsumerDemo(pc);
		
		Thread thread1 = new Thread(producerDemo);
		Thread thread2 = new Thread(consumerDemo);
		
		thread1.start();
		System.out.print("print in main thread.");
//		thread2.start();
	}
}

class ProducerDemo implements Runnable{
	PC pc;
	
	public ProducerDemo(PC pc) {
		this.pc = pc; 
	}
	public void run() {
		pc.producer();
	}
}

class ConsumerDemo implements Runnable{
	PC pc;
	
	public ConsumerDemo(PC pc) {
		this.pc = pc; 
	}
	public void run() {
		pc.consumer();
	}
}

class PC{
	ArrayList<Integer> list= new ArrayList<>();
	int capacity = 15;
	
	public void producer(){
		while (true) {
			int value = new Random().nextInt(100);
			if(list.size()<capacity){
				System.out.print("producer insert: " + value + "\n");
				list.add(value);
			}else
				break;
		}		
	}
	
	public void consumer() {
		while (true) {
			if(list.size() !=0){
				int value = list.remove(0);
				System.out.print("consumer get: " + value + "\n");
			}
			else
				System.out.println("list is empty.");
		}		
	}
}
