package com.zkwp.chat.service.statistics;

import com.zkwp.api.bean.statistics.LikeIssue;
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
 * @date 2020/4/25 14:53
 **/
@Service
public class LikeIssueService {
    private ConcurrentMap<String, LikeIssue> concurrentMap = new ConcurrentHashMap<>();

    @Autowired
    private IStatisticsDao iStatisticsDao;

    @Async
    public void accumulateLikeIssue(String issueId) {
        if (concurrentMap.containsKey(issueId)) {
            concurrentMap.get(issueId).add();
        } else {
            concurrentMap.put(issueId, new LikeIssue(issueId, new AtomicInteger(1)));
        }
    }

    @Scheduled(cron = "0 0/5 * * * ? *")
    private void addDB() {
        ConcurrentMap<String, LikeIssue> temp = concurrentMap;
        concurrentMap.clear();
        Iterator<Map.Entry<String, LikeIssue>> entryIterator = temp.entrySet().iterator();
        List<LikeIssue> list = new ArrayList<>();
        while (entryIterator.hasNext()) {
            Map.Entry<String, LikeIssue> stringSightIssueEntry = entryIterator.next();
            list.add(stringSightIssueEntry.getValue());
        }
        iStatisticsDao.updateLikeIssue(list);
    }
}
