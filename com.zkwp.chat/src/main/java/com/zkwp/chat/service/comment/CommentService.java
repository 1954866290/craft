package com.zkwp.chat.service.comment;

import com.zkwp.api.bean.BizComment;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.chat.dao.comment.ICommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/17 22:06
 **/
@Service
public class CommentService {
    @Autowired
    ICommentDao iCommentDao;

    public List<Map> getCommentsTop5(Map params){
        return iCommentDao.getCommentsTop5(params);
    }

    public int findLikeRecond(String userid,String issueid){
        Map<String,Object> params = new HashMap<>();
        params.put("userid",userid);
        params.put("issueid",issueid);
        Map result =iCommentDao.findLikeRecond(params);
        return  Integer.parseInt(StringUtil.objToString(result.get("count")));
    }

    public CommonResult doComment(Map params){
        params.put("commenttime",StringUtil.dateToString(new Date()));
        params.put("likecount",0);
        params.put("delflag",0);
        params.put("status",0);
        int i = iCommentDao.insertCommentRecord(params);
        return CommonResult.success(i);
    }
}
