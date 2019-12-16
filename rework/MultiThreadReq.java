package rework;

import java.net.HttpURLConnection;
import java.net.URL;


class sendReq{	
	public void sendReqs(){
//		boolean Res = false;
		HttpURLConnection huc;
		
		try {
			URL url = new URL("https://app.e.uban360.com/train/checkstatus");		
			huc = (HttpURLConnection)url.openConnection();			
			System.out.println(huc.getResponseCode());
//			if (huc.getResponseCode() == 200){
//				Res = true;
//			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

//Using sub-class of thread to send requests concurrently
class MultiThreads1 extends Thread{
	private int total;
	private int concurrency;
	Thread t;
	
	sendReq sReq = new sendReq(); 
			
	// by default, the total and concurrency is 1.
	public MultiThreads1() {
		total = 1;
		concurrency = 1;
	}
	
	public MultiThreads1(int totalThreads, int concurrntNum) {
		total = totalThreads;
		concurrency = concurrntNum;
	}
	
	@Override
	public void run(){
		System.out.println(t.currentThread().getName());
//		while (true) {	
//			System.out.println(t.currentThread().getName());
//		}
		sReq.sendReqs();					
	}

	// TODO: use list or threadPool
	public void start() {
		for (int c = 0;c < total;){
			if (Thread.activeCount() > concurrency)
				continue;
			else{
				t = new Thread(this);
				t.start();
				c++;
			}
		}
	}
}

//Using runnable 
class singleThread implements Runnable{
	sendReq sReq;
	
	public singleThread(sendReq sendReq) {
		this.sReq = sendReq;
	}
	
	@Override
	public void run() {
		sReq.sendReqs();		
	}
}

class MultiThreads2{
	private int total;
	private int concurrency;
			
	// by default, the total and concurrency is 1.
	public MultiThreads2() {
		total = 1;
		concurrency = 1;
	}
	
	public MultiThreads2(int totalThreads, int concurrntNum) {
		total = totalThreads;
		concurrency = concurrntNum;
	}
	
	public void start(singleThread sThread) {
		for (int c = 0;c < total;){
			if (Thread.activeCount() > concurrency)
				continue;
			else{
				Thread t = new Thread(sThread);
				t.start();
				c++;
			}
		}
	}

}

class MultiThreadReq{
	public static void main(String[] args) {
		MultiThreads1 mThreads = new MultiThreads1(30,5);
		mThreads.start();
		
//		MultiThreads2 mThreads2 = new MultiThreads2();
//		sendReq sReq = new sendReq();
//		singleThread sThread = new singleThread(sReq);
//		mThreads2.start(sThread);
	}
}