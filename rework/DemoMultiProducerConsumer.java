package rework;
import java.util.LinkedList;
import java.util.List;


public class DemoMultiProducerConsumer {

	public static void main(String[] args) {
		  List<Integer> sharedQueue = new LinkedList<Integer>(); //Creating shared object
		   
		  DemoProducer producer0=new DemoProducer(sharedQueue, 0);
		  DemoConsumer consumer0=new DemoConsumer(sharedQueue);

		  Thread producerThread0 = new Thread(producer0, "ProducerThread0");
		  Thread consumerThread0 = new Thread(consumer0, "ConsumerThread0");
		  producerThread0.start();
		  consumerThread0.start();
		     
		  System.out.println("MID");
		     
		  DemoConsumer consumer1=new DemoConsumer(sharedQueue);
		  Thread consumerThread1 = new Thread(consumer1, "ConsumerThread1");
		  consumerThread1.start();
		  
		  DemoConsumer consumer2=new DemoConsumer(sharedQueue);		  
		  Thread consumerThread2 = new Thread(consumer2, "ConsumerThread2");
		  consumerThread2.start();
		  
		  DemoConsumer consumer3=new DemoConsumer(sharedQueue);		  
		  Thread consumerThread3 = new Thread(consumer3, "ConsumerThread3");
		  consumerThread3.start();
		  
		  DemoConsumer consumer4=new DemoConsumer(sharedQueue);		  
		  Thread consumerThread4 = new Thread(consumer4, "ConsumerThread4");
		  consumerThread4.start();
    }
}


/**
* Producer Class.
*/
class DemoProducer implements Runnable {

 private List<Integer> sharedQueue;
 private int maxSize=2; //maximum number of products which sharedQueue can hold at a time.
 int productionSize=500; //Total no of items to be produced by each producer
 int producerNo;
 
 public DemoProducer(List<Integer> sharedQueue, int producerNo) {
     this.sharedQueue = sharedQueue;
     this.producerNo = producerNo;
 }

 @Override
 public void run() {
     for (int i = 1; i <= productionSize; i++) { //produce products.
         try {
             produce(i);
             Thread.sleep(10);
         } catch (InterruptedException e) {  e.printStackTrace(); }
     }
}

 private void produce(int i) throws InterruptedException {
    synchronized (sharedQueue) {
       //if sharedQuey is full wait until consumer consumes.
       while (sharedQueue.size() == maxSize) {
             System.out.println(Thread.currentThread().getName()+", Queue is full, producerThread is waiting for "
                    + "consumerThread to consume, sharedQueue's size= "+maxSize);
             sharedQueue.wait();
             System.out.println(Thread.currentThread().getName() +" wake up...");
         }

       //Bcz each producer must produce unique product
             //Ex= producer0 will produce 1-5  and producer1 will produce 6-10 in random order
       int producedItem = (productionSize*producerNo)+ i;  
       
       System.out.println(Thread.currentThread().getName() +" Produced : " + producedItem);
       sharedQueue.add(producedItem);
       sharedQueue.notify();
     }
 }
}

/**
* Consumer Class.
*/
class DemoConsumer implements Runnable {
    private List<Integer> sharedQueue;
 public DemoConsumer(List<Integer> sharedQueue) {
     this.sharedQueue = sharedQueue;
 }
 
    @Override
 public void run() {
     while (true) {
         try {
           consume();
           Thread.sleep(10);
         } catch (InterruptedException e) {  e.printStackTrace(); }
     }
 }

 private void consume() throws InterruptedException {
   
    synchronized (sharedQueue) {
       //if sharedQuey is empty wait until producer produces.
       while (sharedQueue.size() == 0) {
           System.out.println(Thread.currentThread().getName()+", Queue is empty, consumerThread is waiting for "
                           + "producerThread to produce, sharedQueue's size= 0");
             sharedQueue.wait();
         }
       
       System.out.println(Thread.currentThread().getName()+", CONSUMED : "+ sharedQueue.remove(0));
       sharedQueue.notify();
       System.out.println(Thread.currentThread().getName()+", thread sleep...");
     }
 }
 
}