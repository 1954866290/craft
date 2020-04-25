package com.zkwp.chat.dao.statistics;

import com.zkwp.api.bean.statistics.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * @auther zhangkun
 * @date 2020/4/23 20:54
 **/
@Mapper
public interface IStatisticsDao {

    int updateSightIssue(@Param("sightIssueList") List<SightIssue> list);

    int updateSightWebSite(@Param("sightWebSite") SightWebSite sightWebSite);

    int insertSightWebSiteCalendar(@Param("sightWebsite") SightWebSite sightWebSite);

    int updateCommentIssue(@Param("commentIssueList") List<CommentIssue> list);

    int updateAttentionUser(@Param("attentionUserList") List<AttentionUser> list);

    int updateLikeComment(@Param("likeCommentList") List<LikeComment> list);

    int updateLikeIssue(@Param("likeIssueList") List<LikeIssue> list);

}
