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
//		eService.getPacks(eService.getMobiles());	
		eService.isSVIP("15088603361");
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
			HWPackageList hwpackageList = new HWPackageList();
			hwpackageList.setParams(params);
			respList.add(pool1.submit(hwpackageList));
			System.out.println("Before call:" + hwpackageList.getParams());
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
	
	public boolean isSVIP(String mobile){
		
		System.out.println(res);
		if (res.size() == 0)
			return false;
		else
			return true;
	}
	
	public void checkVip(String mobile) {
		
	}
	

	
}



class HWPackageList implements Callable<String>{
	private String url = "http://10.0.10.83:7804/migu-activity-thirdapi/backdoor/getPackageList";
	Map<String, String> headers = new HashMap<>();
	Map<String, Object> params = new HashMap<>();

	@Override
	public String call() throws Exception{
		return HttpUtil.get(url, headers, params);		
	}
	
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
	

}

class SMVipInfo implements Callable<String>{
	private static String userName = "root";
	private static String password = "shinemo123";
	private static String url = "jdbc:mysql://10.0.10.41:3306/shinemo_migu_activity";
	private String mobile;
	
	@Override
	public String call() throws Exception {
		DbUtil dbUtil = new DbUtil(userName, password, url);
		String querySql = "select * from svip_order_record where mobile = \""+this.getMobile()"\"order by id desc limit 1;";
		List<Map<String, Object>> res= dbUtil.queryForList(querySql);
		return res.get(0).toString();
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		SMVipInfo.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		SMVipInfo.password = password;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		SMVipInfo.url = url;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
	
}