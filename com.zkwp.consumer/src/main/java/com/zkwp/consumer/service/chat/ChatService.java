package com.zkwp.consumer.service.chat;

import com.zkwp.api.bean.BizComment;
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
public class ChatService {

    @Autowired
    ChatFeignService chatFeignService;
    public List<BizComment> getCommentsTop5(Map params){
        String issueid = StringUtil.objToString(params.get("issueid"));
        return chatFeignService.getCommentsTop5(issueid);
    }

}
