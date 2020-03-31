package com.zkwp.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther zhangkun
 * @date 2020/2/16 12:17
 **/
public class StringUtil {

    public static boolean isEmail(Object args) {
        if(args == null){
            return false;
        }else{
            Pattern p = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
            Matcher m = p.matcher(args.toString());
            return m.matches();
        }
    }

    public static String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    
    /**
     * 生成UUID
*/
public static String uuid() {
	return UUID.randomUUID().toString();
}

/**
* 判断字符串不为空
*/
public static boolean isNotBlank(String param) {
	return param !=null && !"".equals(param.trim()) && !"null".equals(param.toLowerCase());
}

/**
* 判断字符串为空
*/
public static boolean isBlank(String param) {
	return param ==null || "".equals(param.trim());
}


public static String objToString(Object obj){
    if(obj == null) return "";
    return obj.toString();
}
}
