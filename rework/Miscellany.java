package rework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Miscellany{
	public static void main(String[] args) {
		test t1 = new test();
//		t1.testHttpUtil();		
//		t1.testListNestedMap();
		System.out.println(t1.test1() + " in main: "+System.currentTimeMillis());
		System.out.println();
		System.out.println(t1.test2() + " in main: "+System.currentTimeMillis());
		System.out.println(t1.test3() + " in main: "+System.currentTimeMillis());


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
	
	public void testListNestedMap() {
		List<Object> list = new ArrayList<Object>();
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("ele1-key1", "valueOfE1K1");
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("ele2-key1", "valueOfE2K1");
		
		list.add(map1);
		list.add(map2);
		list.add("Element3");
		
		map2.put("ele2-key2", "valueOfE2K2");
		
		System.out.println(list);
//		System.out.println(list.get(1).get("ele2-key2"));
		
		System.out.println(list.get(1));
		Map<String, Object> res = new HashMap<>();
//		res = list.get(1);
	}
	
	public String test1() {
		String aString = "aaa";
		try{
			System.out.println(System.currentTimeMillis());
			return aString;
		}finally{
			System.out.println("in finally block: "+System.currentTimeMillis());
			aString = "bbb";
		}		
	}
	
	public List<Object> test2() {
		List<Object> list1 = new ArrayList<>();
		list1.add("string1");
		
		try{
			return list1;
		}finally{
			System.out.println("in finally block: "+System.currentTimeMillis());
			list1.add("string111");
		}		
	}
	
	public List<Object> test3() {
		List<Object> list1 = new ArrayList<>();
		list1.add("string1");
		list1.add("string111");
		List<Object> list2 = new ArrayList<>();
		list2.add(list1);
		list2.add("string2");
		List<Object> list3 = new ArrayList<>();
		list3.add(list2);
		
		try{
			return list2;
		}finally{
			System.out.println("in finally block: "+System.currentTimeMillis());
			list1.add("string11111");
		}		
	}
}