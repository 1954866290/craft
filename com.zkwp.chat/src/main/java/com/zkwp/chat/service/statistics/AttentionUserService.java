package com.zkwp.chat.service.statistics;

import com.zkwp.api.bean.statistics.AttentionUser;
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
 * @date 2020/4/25 14:27
 **/
@Service
public class AttentionUserService {
    private ConcurrentMap<String, AttentionUser> concurrentMap = new ConcurrentHashMap<>();

    @Autowired
    private IStatisticsDao iStatisticsDao;

    @Async
    public void accumulateAttentionUser(String userId) {
        if (concurrentMap.containsKey(userId)) {
            concurrentMap.get(userId).add();
        } else {
            concurrentMap.put(userId, new AttentionUser(userId, new AtomicInteger(1)));
        }
    }

    @Scheduled(cron = "")
    private void addDB() {
        ConcurrentMap<String, AttentionUser> temp = concurrentMap;
        concurrentMap.clear();
        Iterator<Map.Entry<String, AttentionUser>> entryIterator = temp.entrySet().iterator();
        List<AttentionUser> list = new ArrayList<>();
        while (entryIterator.hasNext()) {
            Map.Entry<String, AttentionUser> stringSightIssueEntry = entryIterator.next();
            list.add(stringSightIssueEntry.getValue());
        }
        iStatisticsDao.updateAttentionUser(list);
    }

}
