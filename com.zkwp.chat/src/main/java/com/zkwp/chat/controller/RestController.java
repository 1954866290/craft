package com.zkwp.chat.controller;

import com.zkwp.api.bean.BizComment;
import com.zkwp.api.bean.statistics.SightWebSite;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.chat.service.comment.CommentService;
import com.zkwp.chat.service.statistics.*;
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

    @Autowired
    private AttentionUserService attentionUserService;

    @Autowired
    private CommentIssueService commentIssueService;

    @Autowired
    private LikeCommentService likeCommentService;

    @Autowired
    private LikeIssueService likeIssueService;

    @Autowired
    private SightWebSiteService sightWebSiteService;

    @Autowired
    private SightIssueService sightIssueService;

    @PostMapping("/Comment/getCommentsTop5")
    public List<Map> getCommentsTop5(@RequestBody Map params) {
        return commentService.getCommentsTop5(params);
    }


    @PostMapping("/Comment/findLikeRecond")
    public int findLikeRecond(@RequestParam("userid") String userid, @RequestParam("issueid") String issueid) {
        return commentService.findLikeRecond(userid, issueid);
    }

    @PostMapping("/Comment/doComment")
    public CommonResult doComment(@RequestBody Map params) {
        try {
            return commentService.doComment(params);
        } catch (Exception e) {
            return CommonResult.failed();
        }
    }

    @PostMapping("/Statistics/addAttentionUser")
    public void addAttentionUser(@RequestParam("userid") String userId) {
        attentionUserService.accumulateAttentionUser(userId);
    }

    @PostMapping("/Statistics/addCommentIssue")
    public void addCommentIssue(@RequestParam("issueid") String issueId) {
        commentIssueService.accumulateCommentIssue(issueId);
    }

    @PostMapping("/Statistics/addLikeComment")
    public void addLikeComment(@RequestParam("commentid") String commentId) {
        likeCommentService.accumulateLikeComment(commentId);
    }

    @PostMapping("/Statistics/addLikeIssue")
    public void addLikeIssue(@RequestParam("issueid") String issueId) {
        likeIssueService.accumulateLikeIssue(issueId);
    }

    @PostMapping("/Statistics/addSightIssue")
    public void addSightIssue(@RequestParam("issueid") String issueId) {
        sightIssueService.accumulateSightIssue(issueId);
    }

    @PostMapping("/Statistics/addSightWebSite")
    public void addSightWebSite() {
        sightWebSiteService.accumulateSightWebSite();
    }


}
