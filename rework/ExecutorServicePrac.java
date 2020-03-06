package rework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/*** This class is a practice of executorService, httprequst and db ops.
* @author Ivana.H
*/
public class ExecutorServicePrac{
	public static void main(String[] args) {
		EService eService = new EService();
		eService.getPacks(eService.getMobiles());	
	}
}
	
class EService{
	public static ExecutorService pool1 =  Executors.newFixedThreadPool(2);
	public static List<String> mobiles = new ArrayList<>();
	
	public List<String> getMobiles() {
		mobiles.add("15088603364");
		mobiles.add("15068746748");
		mobiles.add("15957193120");
		mobiles.add("15958032925");
		mobiles.add("18458872034");
		return mobiles;
	}
	
	public void getPacks(List<String> mobiles) {
		List<Future> respList = new ArrayList<Future>();
		
		for(String mobile : mobiles){
			Map<String, Object> params = new HashMap<>();
			params.put("mobile", mobile);
			PackageList packageList = new PackageList();
			packageList.setParams(params);
			respList.add(pool1.submit(packageList));
			System.out.println("Before call:" + packageList.getParams());
		}
				
		for (Future f : respList){
			try {
				System.out.println(f.get().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

class PackageList implements Callable<String>{
	private String url = "http://10.0.10.83:7804/migu-activity-thirdapi/backdoor/getPackageList";
	Map<String, String> headers = new HashMap<>();
	Map<String, Object> params = new HashMap<>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	@Override
	public String call() throws Exception{
		return HttpUtil.get(url, headers, params);		
	}
}