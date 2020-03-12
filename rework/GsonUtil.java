package rework;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.*;

public class GsonUtil {

	private static final Logger log = LoggerFactory.getLogger(GsonUtil.class);

	private final static Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	public static <T> String fromObj2Gson(T obj, Class<T> clazz) {
		if (null == obj)
			return null;
		return gson.toJson(obj, clazz);
	}

	public static <T> T fromGson2Obj(String json, Class<T> clazz) {
		if (json == null || json.equals("")) {
			return null;
		}
		if (clazz.getSimpleName().equals(String.class.getSimpleName())) {
			return (T) String.valueOf(json);
		}
		return gson.fromJson(json, clazz);
	}

	public static <T> String fromObj2Gson(T obj, Type t) {
		if (null == obj)
			return null;
		return gson.toJson(obj, t);
	}

	public static <T> List<T> fromJsonToList(String json, Class<T[]> type) {
		try {
			T[] list = gson.fromJson(json, type);
			if (list == null)
				return null;
			return Arrays.asList(list);
		} catch (Exception e) {
			log.error("Jsons.fromJsonToList ex, json=" + json + ", type=" + type, e);
		}
		return null;
	}

	public static <T> T fromGson2Obj(String json, Type type) {
		if (json == null || json.equals("")) {
			return null;
		}
		return gson.<T>fromJson(json, type);
	}

	public static <T> T fromGson2Obj(String json, TypeToken<T> typeToken) {
		if (json == null || json.equals("")) {
			return null;
		}
		if (typeToken == null)
			throw new IllegalArgumentException("type token is null");
		return gson.fromJson(json, typeToken.getType());
	}

	public static Map<String, Object> getJsonMap(String jsonStr) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (jsonStr != null && !jsonStr.equals("")) {
			Type typeOfT = new TypeToken<Map<String, Object>>() {
			}.getType();
			paramMap = gson.fromJson(jsonStr, typeOfT);
		}
		return paramMap;
	}

	public static String toJson(Object object) {
		if (object instanceof String) {
			return (String) object;
		}
		return gson.toJson(object);
	}

	public static void main(String[] args) {

		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("a", "1");
		list.add(map);
		list.add(map);
		System.out.println(toJson(list));
	}
}