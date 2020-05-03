package com.zkwp.issue.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类
 * 
 * @author 王朋
 *
 */
public class JsonUtil {

	private static ObjectMapper objectMapper;

	@SuppressWarnings({ "unused", "rawtypes" })
	private static Map convertorMap = new HashMap();
	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	private JsonUtil() {
	}

	/**
	 * 将object转换为json
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String convertObject2Json(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json转换为object
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static Object convertJson2Object(String json, Class cls) {
		try {
			return objectMapper.readValue(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
