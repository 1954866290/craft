package com.zkwp.chat.service.statistics;

import com.zkwp.api.bean.statistics.CommentIssue;
import com.zkwp.chat.dao.statistics.IStatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangkun
 * @date 2020/4/25 13:48
 **/
@Service
public class CommentIssueService {
    private ConcurrentMap<String, CommentIssue> stringCommentIssueConcurrentMap = new ConcurrentHashMap<>();

    @Autowired
    private IStatisticsDao iStatisticsDao;

    @Async
    public void accumulateCommentIssue(String issueId) {
        if (stringCommentIssueConcurrentMap.containsKey(issueId)) {
            stringCommentIssueConcurrentMap.get(issueId).add();
        } else {
            stringCommentIssueConcurrentMap.put(issueId, new CommentIssue(issueId, new AtomicInteger(1)));
        }
    }

    @Scheduled(cron = "0 0/5 * * * ? *")
    private void addDB() {
        ConcurrentMap<String, CommentIssue> temp = stringCommentIssueConcurrentMap;
        stringCommentIssueConcurrentMap.clear();
        Iterator<Map.Entry<String, CommentIssue>> entryIterator = temp.entrySet().iterator();
        List<CommentIssue> list = new ArrayList<>();
        while (entryIterator.hasNext()) {
            Map.Entry<String, CommentIssue> stringSightIssueEntry = entryIterator.next();
            list.add(stringSightIssueEntry.getValue());
        }
        iStatisticsDao.updateCommentIssue(list);
    }

}
