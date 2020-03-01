package com.zkwp.system.controller;

import com.zkwp.system.constant.CacheConstant;
import com.zkwp.system.service.EmailService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.User;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.system.service.UserService;
import com.zkwp.system.util.RedisUtils;


/**
 * 用户登录注册模块
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static int CodeExpireTime = 60;   // redis中存储的短信验证码过期时间60s
	private static String sendCodeArea = "cn-hangzhou";// 发送短信地区
	private static String AccessKeyID = "LTAI4Fj7ZYbW2ZYAbTdWjopT";// 短信服务KeyID
	private static String AccessKeySecret = "O9xGVOyW1CpS2g9oSfYqF0Ds18uALg";// 短信服务KeySecret
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private EmailService emailService;
//    @Autowired
//    private CacheConstant cacheConstant;
    
   /**
    * 发送短信验证码接口
    * "0000"表示成功
    * @param billId
    * @return
    */
   @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
   public OutputObject sendCode(String billId, HttpServletRequest request1, HttpSession session) {
	   OutputObject out = new OutputObject();
	   boolean isBillId = isPhoneNum(billId);
	   User user = new User();
	   String randomCode = createRandomCode();
	   // 参数为手机号
	   if (isBillId) {
		   user = userService.getUserInfoByPhone(billId);
		   if (StringUtil.isNotBlank(user.toString())) {
			   out = sendRandomCode(billId, randomCode, request1);
		   } else {
			   userService.userRegister(user);
			   out = sendRandomCode(billId, randomCode, request1);
		   }
	   } else {
		   // 参数为邮箱
		   user = userService.getUserInfoByEmail(billId);
		   if (StringUtil.isNotBlank(user.toString())) {
			   emailService.sendSimpleMail(billId, "手工艺品推广平台","验证码为：" + randomCode + "，您正在登录，若非本人操作，请勿泄露。");
			   out.setReturnCode(CacheConstant.SEND_CODE_SUCCESS_RETURN_CODE);
	           out.setReturnMessage(CacheConstant.SEND_CODE_SUCCESS_RETURN_MESSAGE);
	           // 获取访问者的真实ip地址
	           String requestIp = getIpAddr(request1);
	           redisUtils.set("randomCodeRedis" + requestIp, randomCode, CodeExpireTime);// 将邮箱验证码放入到redis缓存中,失效时间60s
		   } else {
			   userService.userRegister(user);
			   emailService.sendSimpleMail(billId, "手工艺品推广平台","验证码为：" + randomCode + "，您正在登录，若非本人操作，请勿泄露。");
			   out.setReturnCode(CacheConstant.SEND_CODE_SUCCESS_RETURN_CODE);
	           out.setReturnMessage(CacheConstant.SEND_CODE_SUCCESS_RETURN_MESSAGE);
	           // 获取访问者的真实ip地址
	           String requestIp = getIpAddr(request1);
	           redisUtils.set("randomCodeRedis" + requestIp, randomCode, CodeExpireTime);// 将邮箱验证码放入到redis缓存中,失效时间60s
		   }
       }
	   session.setAttribute(billId, user);
	   return out;
   }
   /*
    * 发送短信验证码
    */
   private  OutputObject sendRandomCode(String billId, String code, HttpServletRequest request1) {
	   OutputObject out = new OutputObject();
	   DefaultProfile profile = DefaultProfile.getProfile(sendCodeArea, AccessKeyID, AccessKeySecret);
       IAcsClient client = new DefaultAcsClient(profile);
       // 封装发送短信请求参数
       CommonRequest request = new CommonRequest();
       request.setMethod(MethodType.POST);
       request.setDomain("dysmsapi.aliyuncs.com");
       request.setVersion("2017-05-25");
       request.setAction("SendSms");
       request.putQueryParameter("RegionId", "cn-hangzhou");
       request.putQueryParameter("PhoneNumbers", billId);
       request.putQueryParameter("SignName", "手工艺品推广平台");
       request.putQueryParameter("TemplateCode", "SMS_183766962");
       request.putBodyParameter("TemplateParam", "{ \"code\":\"" + code + "\"}");
       try {
           CommonResponse response = client.getCommonResponse(request);
           if ("200".equals(response.getHttpStatus())) {// 200表示短信发送成功
           	out.setReturnCode(CacheConstant.SEND_CODE_SUCCESS_RETURN_CODE);
           	out.setReturnMessage(CacheConstant.SEND_CODE_SUCCESS_RETURN_MESSAGE);
           } else {
           	out.setReturnCode(CacheConstant.SEND_CODE_FAIL_RETURN_CODE);
           	out.setReturnMessage(CacheConstant.SEND_CODE_FAIL_RETURN_MESSAGE);
           }
           // 获取访问者的真实ip地址
           String requestIp = getIpAddr(request1);
           redisUtils.set("randomCodeRedis" + requestIp, code, CodeExpireTime);// 将短信验证码放入到redis缓存中,失效时间60s
       } catch (ServerException e) {
           e.printStackTrace();
       } catch (ClientException e) {
           e.printStackTrace();
       }
       return out;
   }
   
   /*
    * 短信验证码校验接口
    */
   @RequestMapping(value = "/checkRandomCode", method = RequestMethod.POST)
   public OutputObject checkRandomCode(String code, HttpServletRequest request) {
	   OutputObject out = new OutputObject();
	   String requestIp = getIpAddr(request);
	   String randomCode = (String)redisUtils.get("randomCodeRedis" + requestIp);// 从redis缓存中获取短信验证码
	   if (StringUtil.isBlank(randomCode)) {
		   out.setReturnCode(CacheConstant.RANDOM_CODE_IS_BLANK_RETURN_CODE);
		   out.setReturnMessage(CacheConstant.RANDOM_CODE_IS_BLANK_RETURN_MESSAGE);
	   } else if (randomCode.equals(code)){
		   out.setReturnCode(CacheConstant.LOGIN_SUCCESS_RETURN_CODE);
		   out.setReturnMessage(CacheConstant.LOGIN_SUCCESS_RETURN_MESSAGE);//表示登陆成功
	   } else {
		   out.setReturnCode(CacheConstant.RANDOM_CODE_NO_SAME_RETURN_CODE);
		   out.setReturnMessage(CacheConstant.RANDOM_CODE_NO_SAME_RETURN_MESSAGE);
	   }
	   return out;
   }
   
   /*
    * 注册
    */
   public OutputObject userRegister(String phone, String email, String password) {
	   User user = new User();
	   OutputObject out = new OutputObject();
	   if (StringUtil.isNotBlank(phone)) {
		   user.setPhone(phone);
	   }
	   if (StringUtil.isNotBlank(email)) {
		   user.setEmail(email);
	   }
	   if (StringUtil.isNotBlank(password)) {
		   user.setPassword(password);
	   }
	   this.userService.userRegister(user);
	   out.setReturnCode("0000");
	   out.setReturnMessage("注册成功");
	   return out;
   }
   
   /**
    * 判断是否为手机号
    * @return
    */
   private static boolean isPhoneNum(String billId) {
	   Pattern p = null;
	   Matcher m = null;
	   boolean b = false;
	   p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
	   m = p.matcher(billId);
	   b = m.matches();
	   return b;
   }
   
   /**
    * 生成6位随机码
    */
   private static String createRandomCode(){
		String randomCode = "";
       for (int i = 0; i < 6; i++) {
    	   randomCode = randomCode + (int)(Math.random() * 9);
       }
       return randomCode;
	}	
   /*
    * 获取访问者真实ip
    */
    private static String getIpAddr(HttpServletRequest request) {
	            String ip = request.getHeader("X-Real-IP");
	            if (StringUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	                return ip;
	            }
	            ip = request.getHeader("X-Forwarded-For");
	           if (StringUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	                // 多次反向代理后会有多个IP值，第一个为真实IP。
	                int index = ip.indexOf(',');
	                if (index != -1) {
	                    return ip.substring(0, index);
	                } else {
	                   return ip;
	                }
	            } else {
	                return request.getRemoteAddr();
	            }
	        }

}
