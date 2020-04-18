package com.zkwp.consumer.controller.chat;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.controller.issue.IssueController;
import com.zkwp.consumer.service.chat.CommentService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/18 9:40
 **/
@Controller
@RequestMapping(value = "/chat")
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/Comment/doComment")
    @ResponseBody
    public CommonResult doComment(HttpServletRequest request, HttpSession session, @RequestParam("cover") MultipartFile cover, @Param("video")MultipartFile video) {
        Map params  = RestUtil.getParameterMap(request);
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        params.put("userid",userid);
        try{
            return commentService.doComment(params);
        }catch (Exception e){
            logger.error("IssueController::doIssue throws exception",e);
            return CommonResult.failed();
        }
    }
}
