package com.zkwp.system.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.system.constant.CacheConstant;
import com.zkwp.system.dao.ILoginDao;
import com.zkwp.system.dao.WechatUserDao;
import com.zkwp.system.util.RedisUtils;

@Service
public class WechatLoginService {
	
	private static int CodeExpireTime = 5 * 60;   // redis中存储的短信验证码过期时间5分钟
    private static String sendCodeArea = "cn-hangzhou";// 发送短信地区
    private static String AccessKeyID = "LTAI4Fj7ZYbW2ZYAbTdWjopT";// 短信服务KeyID
    private static String AccessKeySecret = "O9xGVOyW1CpS2g9oSfYqF0Ds18uALg";// 短信服务KeySecret

    @Autowired
    private RedisUtils redisUtils;
    
    @Autowired
	private WechatUserDao wechatUserDao;
    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    public OutputObject sendCodeWechat(@PathVariable("phone") String phone) {
        OutputObject out = new OutputObject();
        String randomCode = createRandomCode();
        out = sendRandomCodeWechat(phone, randomCode);
        return out;
    }
    /**
     * 校验短信验证码和用户身份信息
     * @param phone
     * @param code
     * @return
     */
    public OutputObject checkCode(String phone, String code) {
    	OutputObject out = new OutputObject();
        String randomCode = (String) redisUtils.get("randomCodeRedis_" + phone);// 从redis缓存中获取短信验证码
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (code.equals(randomCode)) {
        	User user = wechatUserDao.getUserInfoByPhone(phone);
            if (user!=null) {
            	out.setReturnCode("0000");
            	out.setReturnMessage("用户已存在");
            }else {
            	User userNew = new User();
            	userNew.setPhone(phone);
            	userNew.setUsercreatetime(sdf.format(now));
            	wechatUserDao.createUserWechat(userNew);
            	out.setReturnCode("00");
            	out.setReturnMessage("用户注册成功");
            }
        } else {
        	out.setReturnCode("9999");
        	out.setReturnMessage("验证码错误");
        }
        return out;
    }

    private OutputObject sendRandomCodeWechat(String phone, String code) {
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
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "手工艺品推广平台");
        request.putQueryParameter("TemplateCode", "SMS_183766962");
        request.putBodyParameter("TemplateParam", "{ \"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            if ("200".equals(String.valueOf(response.getHttpStatus()))) {// 200表示短信发送成功
                out.setReturnCode(CacheConstant.SEND_CODE_SUCCESS_RETURN_CODE);
                out.setReturnMessage(CacheConstant.SEND_CODE_SUCCESS_RETURN_MESSAGE);
            } else {
                out.setReturnCode(CacheConstant.SEND_CODE_FAIL_RETURN_CODE);
                out.setReturnMessage(CacheConstant.SEND_CODE_FAIL_RETURN_MESSAGE);
            }
            redisUtils.set("randomCodeRedis_" + phone, code, CodeExpireTime);// 将短信验证码放入到redis缓存中,失效时间60s
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return out;
    }


    /**
     * 生成6位随机码
     */
    private static String createRandomCode() {
        String randomCode = "";
        for (int i = 0; i < 6; i++) {
            randomCode = randomCode + (int) (Math.random() * 9);
        }
        return randomCode;
    }


}
