package com.zkwp.api.controller;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/14 22:12
 **/
public class BaseController {

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
        return returnMap;
    }

    protected String returnSuccess(Map retMap, JSONObject json) throws Exception {
        retMap.put("status", "200");
        String jsoStr = JSONObject.toJSONString(retMap).replace("\t", " ");
        return jsoStr;
    }

    protected String returnException(Map params, Exception e, JSONObject json) {
        String usercode = (String)params.get("usercode");
        json.put("resultCode", "404");
        json.put("error", "请联系开发人员");
        json.put("details", e.getMessage());
        return json.toJSONString();
    }

}
