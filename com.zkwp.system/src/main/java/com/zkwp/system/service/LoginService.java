package com.zkwp.system.service;

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
import com.zkwp.system.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther zhangkun
 * @date 2020/4/11 15:47
 **/
@Service
public class LoginService {
    private static int CodeExpireTime = 5 * 60;   // redis中存储的短信验证码过期时间60s
    private static String sendCodeArea = "cn-hangzhou";// 发送短信地区
    private static String AccessKeyID = "";// 短信服务KeyID
    private static String AccessKeySecret = "";// 短信服务KeySecret

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ILoginDao iLoginDao;

    public CommonResult sendCode(String type, String code, String ip) {
        OutputObject out = new OutputObject();
        String randomCode = createRandomCode();
        // 参数为手机号
        if (type.equals("phone")) {
            sendRandomCode(code, randomCode, ip);
            redisUtils.set(code + ip, randomCode, CodeExpireTime);
        } else {
            // 参数为邮箱
            emailService.sendSimpleMail(code, "手工艺品推广平台", "验证码为：" + randomCode + "，您正在登录，若非本人操作，请勿泄露。");
            out.setReturnCode(CacheConstant.SEND_CODE_SUCCESS_RETURN_CODE);
            out.setReturnMessage(CacheConstant.SEND_CODE_SUCCESS_RETURN_MESSAGE);
            // 获取访问者的真实ip地址
            redisUtils.set(code + ip, randomCode, CodeExpireTime);
        }
        return CommonResult.success(1);
    }


    public CommonResult doLogin(String type, String code, String verify, String ip) {
        String randomCode = (String) redisUtils.get(code + ip);// 从redis缓存中获取短信验证码
        if (verify.equals(randomCode)) {
            if (type.equals("email")) {
                User user = iLoginDao.getUserByEmail(code);
                if (user == null) {
                    user = new User();
                    user.setEmail(code);
                    user.setPhone(" ");
                    user.setNickname("###" + randomCode);
                    user.setUsercreatetime(StringUtil.dateToString(new Date()));
                    iLoginDao.createUser(user);
                }
                return CommonResult.success(user.getUserid());
            } else {
                User user = iLoginDao.getUserByPhone(code);
                if(user == null){
                    user = new User();
                    user.setPhone(code);
                    user.setEmail(" ");
                    user.setNickname("###" + randomCode);
                    user.setUsercreatetime(StringUtil.dateToString(new Date()));
                    int res = iLoginDao.createUser(user);
                    System.out.println(res);
                }
                return CommonResult.success(user.getUserid());
            }
        } else {
            return CommonResult.failed("验证码错误");
        }
    }

    private OutputObject sendRandomCode(String phone, String code, String ip) {
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
            if ("200".equals(response.getHttpStatus())) {// 200表示w短信发送成功
                out.setReturnCode(CacheConstant.SEND_CODE_SUCCESS_RETURN_CODE);
                out.setReturnMessage(CacheConstant.SEND_CODE_SUCCESS_RETURN_MESSAGE);
            } else {
                out.setReturnCode(CacheConstant.SEND_CODE_FAIL_RETURN_CODE);
                out.setReturnMessage(CacheConstant.SEND_CODE_FAIL_RETURN_MESSAGE);
            }
            // 获取访问者的真实ip地址
            String requestIp = ip;
            redisUtils.set("randomCodeRedis" + requestIp, code, CodeExpireTime);// 将短信验证码放入到redis缓存中,失效时间60s
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
