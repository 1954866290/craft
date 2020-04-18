package com.zkwp.consumer.service.chat;

import com.zkwp.api.bean.BizComment;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.feign.ChatFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/16 21:31
 **/
@Service
public class CommentService {

    @Autowired
    ChatFeignService chatFeignService;
    public List<Map> getCommentsTop5(Map params){
        return chatFeignService.getCommentsTop5( params);
    }

    public int findLikeRecond(String userid,String issueid){
        return chatFeignService.findLikeRecond(userid,issueid);
    }

    public CommonResult doComment(Map param){
        return chatFeignService.doComment(param);
    }

}
