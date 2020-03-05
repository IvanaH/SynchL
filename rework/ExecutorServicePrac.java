package rework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.RequestBody;

/*** This class is a practice of executorService, httprequst and db ops.
* @author Ivana.H
* @version 1.0
*/
public class ExecutorServicePrac{
	public static void main(String[] args) {
		EService eService = new EService();
		eService.getPacks();	
	}
}
	
class EService{
	public static ExecutorService pool1 =  Executors.newFixedThreadPool(2);
	
	public void getPacks() {
//		List<Future> respList = new ArrayList<Future>();
//		respList.add(pool1.submit(new PackageList()));
//		
//		for (Future f : respList){
//			try {
//				System.out.println(f.get().toString());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
		
		Future f = pool1.submit(new PackageList());
		try {
			System.out.println(f.get().toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class PackageList implements Runnable{
	private String method = "GET";
	private String url = "http://10.0.10.83:7804/migu-activity-thirdapi/backdoor/getPackageList";
	private RequestBody body = null;
	private HashMap<String, String> headers = new HashMap<String, String>();
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RequestBody getBody() {
		return body;
	}

	public void setBody(RequestBody body) {
		this.body = body;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public void run() {
		headers.put("mobile", "15088603364");
		HttpUtil.invoke(method, url, body, headers);		
	}
}