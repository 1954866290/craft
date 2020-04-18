package com.zkwp.chat.dao.comment;

import com.zkwp.api.bean.BizComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/17 22:07
 **/
@Mapper
public interface ICommentDao {
    List<Map> getCommentsTop5(Map params);

    Map findLikeRecond(Map params);

    int insertCommentRecord(Map params);
}
