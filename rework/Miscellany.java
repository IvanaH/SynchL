package rework;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Miscellany{
	public static void main(String[] args) {
		test t1 = new test();
		t1.testHttpUtil();		
	}
}

class test{
	public void testMap() {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("etst", "vtest");
	}
	
	public void testHttpUtil() {
		String url = "http://10.0.10.83:7804/migu-activity-thirdapi/backdoor/getPackageList";
		Map<String, String> headers = new HashMap<>();
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", "15088603364");
		System.out.println(HttpUtil.get(url, headers,params));
		
	}
}