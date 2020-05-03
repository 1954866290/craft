package com.zkwp.chat.service.statistics;

import com.zkwp.api.bean.statistics.LikeComment;
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

/*
 * @auther zhangkun
 * @date 2020/4/25 14:46
 */

@Service
public class LikeCommentService {
    private ConcurrentMap<String, LikeComment> concurrentMap = new ConcurrentHashMap<>();

    @Autowired
    private IStatisticsDao iStatisticsDao;

    @Async
    public void accumulateLikeComment(String commentId) {
        if (concurrentMap.containsKey(commentId)) {
            concurrentMap.get(commentId).add();
        } else {
            concurrentMap.put(commentId, new LikeComment(commentId, new AtomicInteger(1)));
        }
    }

    @Scheduled(cron = "0 0/5 * * * ? *")
    private void addDBLikeComment() {
        ConcurrentMap<String, LikeComment> temp = concurrentMap;
        concurrentMap.clear();
        Iterator<Map.Entry<String, LikeComment>> entryIterator = temp.entrySet().iterator();
        List<LikeComment> list = new ArrayList<>();
        while (entryIterator.hasNext()) {
            Map.Entry<String, LikeComment> stringSightIssueEntry = entryIterator.next();
            list.add(stringSightIssueEntry.getValue());
        }
        iStatisticsDao.updateLikeComment(list);
    }
}
