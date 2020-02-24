package com.zkwp.system.controller;

import com.zkwp.system.service.EmailService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.zkwp.api.utils.RedisUtils;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.system.service.UserService;


/**
 * 用户登录注册模块
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static int CodeExpireTime = 60;   // redis中存储的短信验证码过期时间60s
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;
    
   /**
    * 发送短信验证码接口
    * "0000"表示成功
    * @param billId
    * @return
    */
   @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
   public OutputObject sendCode(String billId) {
	   OutputObject out = new OutputObject();
	   boolean isBillId = isPhoneNum(billId);
	   if (isBillId) {
		  String randomCode = createRandomCode();
		  DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fj7ZYbW2ZYAbTdWjopT", "O9xGVOyW1CpS2g9oSfYqF0Ds18uALg");
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
	        request.putBodyParameter("TemplateParam", "{ \"code\":\"" + randomCode + "\"}");
	        try {
	            CommonResponse response = client.getCommonResponse(request);
	            if ("200".equals(response.getHttpStatus())) {// 200表示短信发送成功
	            	out.setReturnCode("0000");
	            	out.setReturnMessage("success");
	            }
	            redisUtils.set("randomCodeRedis", randomCode, CodeExpireTime);// 将短信验证码放入到redis缓存中,失效时间60s
	        } catch (ServerException e) {
	            e.printStackTrace();
	        } catch (ClientException e) {
	            e.printStackTrace();
	        }
	   }
	   return out;
   }
   
   /*
    * 短信验证码校验接口
    */
   @RequestMapping(value = "/checkRandomCode", method = RequestMethod.POST)
   public OutputObject checkRandomCode(String code) {
	   OutputObject out = new OutputObject();
	   String randomCode = (String)redisUtils.get("randomCodeRedis");// 从redis缓存中获取短信验证码
	   if (StringUtil.isBlank(randomCode)) {
		   out.setReturnCode("2999");
		   out.setReturnMessage("请重新获取");
	   } else if (randomCode.equals(code)){
		   out.setReturnCode("0000");
		   out.setReturnMessage("短信验证码校验成功");//表示登陆成功
	   } else {
		   out.setReturnCode("2997");
		   out.setReturnMessage("短信验证码校验失败");
	   }
	   return out;
   }
   
   /*
    * 根据邮箱、密码登录接口
    */
   @RequestMapping(value = "/loginByEmail", method = RequestMethod.POST)
   public OutputObject loginByUserInfo(String email, String password) {
	   OutputObject out = new OutputObject();
	   if (StringUtil.isNotBlank(email)) {
		   User userInfo = userService.getUserInfoByUsername(email);
		   String dataBasePwd = userInfo.getPassword();
		   if (StringUtil.isNotBlank(dataBasePwd) && dataBasePwd.equals(password)) {
			   out.setReturnCode("0000");
			   out.setReturnMessage("success");//表示登陆成功
		   }
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

    @Autowired
    EmailService emailService;

    @RequestMapping("/sendEmail")
    public void sendEmail(){
        for (int i = 0 ; i<10;i++)
        emailService.sendSimpleMail("2647808433@qq.com","小宝贝","爸爸爱你");
    }


}
