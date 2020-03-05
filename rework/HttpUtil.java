package rework;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

	private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

	public static Response doInvoke(String method, String url, RequestBody body, Map<String, String> headers) {
		Request.Builder builder = new Request.Builder().url(url).method(method, body);
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				builder.addHeader(entry.getKey(), entry.getValue());
			}
		}
		Request request = builder.build();

		try {
			Response response = client.newCall(request).execute();
			return response;
		} catch (IOException e) {
			log.error("invoke error:{},ex{}", url, e);
		}
		return null;
	}
	
	public static String invoke(String method, String url, RequestBody body, Map<String, String> headers) {
		Response response = doInvoke(method, url, body, headers);
		if(response!=null) {
			if(response.code() == 200) {
				try {
					return response.body().string();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static Response httpPost(String url,String content, Map<String, String> headers) {
		return doInvoke("POST", url, RequestBody.create(MediaType.parse("application/json"), content), headers);
	}

	public static Response httpPost(String url, MediaType mediaType,String content, Map<String, String> headers) {
		return doInvoke("POST", url, RequestBody.create(mediaType, content), headers);
	}

	public static Response httpGet(String url, Map<String, String> headers) {
		return doInvoke("GET", url, null, headers);
	}
	
	public static String post(String url,String content, Map<String, String> headers) {
		return invoke("POST", url, RequestBody.create(MediaType.parse("application/json"), content), headers);
	}

	public static String post(String url, MediaType mediaType,String content, Map<String, String> headers) {
		return invoke("POST", url, RequestBody.create(mediaType, content), headers);
	}

	public static String get(String url, Map<String, String> headers) {
		return invoke("GET", url, null, headers);
	}
	
	public static void main(String[] args) {
		
		String ret = get("https://admin.jituancaiyun.com/aceproxy/checkstatus", null);
		System.out.println(ret);
	}
	

}
