package com.zkwp.chat.controller;

import com.zkwp.api.bean.BizComment;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.chat.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/15 14:42
 **/
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/feign-api")
public class RestController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/Comment/getCommentsTop5")
    public List<Map> getCommentsTop5(@RequestBody Map params){
        return commentService.getCommentsTop5(params);
    }



    @PostMapping("/Comment/findLikeRecond")
    public  int findLikeRecond(@RequestParam("userid")String userid, @RequestParam("issueid")String issueid){
        return commentService.findLikeRecond(userid,issueid);
    }
    @PostMapping("/Comment/doComment")
    public CommonResult doComment(@RequestBody Map params){
        try{
            return commentService.doComment(params);
        }catch (Exception e){
            return CommonResult.failed();
        }
    }
}
