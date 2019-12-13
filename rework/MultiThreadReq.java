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
class multiThreads extends Thread{
	private int total;
	private int concurrency;
	Thread t;
	
	sendReq sReq = new sendReq(); 
			
	// by default, the total and concurrency is 1.
	public multiThreads() {
		total = 1;
		concurrency = 1;
	}
	
	public multiThreads(int totalThreads, int concurrntNum) {
		total = totalThreads;
		concurrency = concurrntNum;
	}
	
	@Override
	public void run(){
		System.out.println(t.currentThread().getName());
		sReq.sendReqs();					
	}

	// 线程池实现：简版线程池 or fixed线程池
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


class MultiThreadReq{
	public static void main(String[] args) {
		multiThreads mThreads = new multiThreads(30,5);
		mThreads.start();
	}
}