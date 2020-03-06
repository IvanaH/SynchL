package rework;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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

	public static Response doInvoke(String method, String url, RequestBody body, Map<String, String> headers,Map<String, Object> params) {
		if (params!=null&&params.size()>0) {
			StringBuffer urls = new StringBuffer(url);
			urls.append("?");
			for (Iterator<Entry<String, Object>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				try {
					urls.append(key).append("=").append(URLEncoder.encode(String.valueOf(value), "utf-8")).append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			url = urls.toString().substring(0, urls.lastIndexOf("&"));
		}
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
	
	public static String invoke(String method, String url, RequestBody body, Map<String, String> headers,Map<String, Object> params) {
		Response response = doInvoke(method, url, body, headers,params);
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
	
	public static Response httpPost(String url,String content, Map<String, String> headers,Map<String, Object> params) {
		return doInvoke("POST", url, RequestBody.create(MediaType.parse("application/json"), content), headers,params);
	}

	public static Response httpPost(String url, MediaType mediaType,String content, Map<String, String> headers,Map<String, Object> params) {
		return doInvoke("POST", url, RequestBody.create(mediaType, content), headers,params);
	}

	public static Response httpGet(String url, Map<String, String> headers,Map<String, Object> params) {
		return doInvoke("GET", url, null, headers,params);
	}
	
	public static String post(String url,String content, Map<String, String> headers,Map<String, Object> params) {
		return invoke("POST", url, RequestBody.create(MediaType.parse("application/json"), content), headers,params);
	}

	public static String post(String url, MediaType mediaType,String content, Map<String, String> headers,Map<String, Object> params) {
		return invoke("POST", url, RequestBody.create(mediaType, content), headers,params);
	}

	public static String get(String url, Map<String, String> headers,Map<String, Object> params) {
		return invoke("GET", url, null, headers,params);
	}
	
	public static void main(String[] args) {
		
		String ret = get("https://admin.jituancaiyun.com/aceproxy/checkstatus", null,null);
		System.out.println(ret);
	}
	

}
