package com.zkwp.api.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/1 21:05
 **/
public class RestUtil {
    public static MultiValueMap getParameterMultiValueMap(HttpServletRequest request) {
        Map properties = request.getParameterMap();
        MultiValueMap<String,Object> returnMap = new LinkedMultiValueMap<>();
        Iterator entries = properties.entrySet().iterator();
        String name = "";
        String value = "";
        Object valueObj;
        while(entries.hasNext()) {
            Map.Entry entry = (Map.Entry)entries.next();
            name = (String)entry.getKey();
            valueObj = entry.getValue();
            if (valueObj == null) {
                value = "";
            } else if (!(valueObj instanceof String[])) {
                value = valueObj.toString();
            } else {
                value = "";
                String[] values = (String[])valueObj;
                for(int i = 0; i < values.length; ++i) {
                    value = value + values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            value = value.trim();
            returnMap.add(name, value);
        }
        returnMap.add("ip",request.getRemoteAddr());
        return returnMap;
    }

    public static Map getParameterMap(HttpServletRequest request) {
        Map properties = request.getParameterMap();
        Map<String,Object> returnMap = new HashMap<>();
        Iterator entries = properties.entrySet().iterator();
        String name = "";
        String value = "";
        Object valueObj;
        while(entries.hasNext()) {
            Map.Entry entry = (Map.Entry)entries.next();
            name = (String)entry.getKey();
            valueObj = entry.getValue();
            if (valueObj == null) {
                value = "";
            } else if (!(valueObj instanceof String[])) {
                value = valueObj.toString();
            } else {
                value = "";
                String[] values = (String[])valueObj;
                for(int i = 0; i < values.length; ++i) {
                    value = value + values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            value = value.trim();
            returnMap.put(name, value);
        }
        returnMap.put("ip",request.getRemoteAddr());
        return returnMap;
    }

}
