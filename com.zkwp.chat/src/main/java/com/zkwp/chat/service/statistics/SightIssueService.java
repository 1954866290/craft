package com.zkwp.chat.service.statistics;

import com.zkwp.api.bean.statistics.SightIssue;
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
 * @date 2020/4/22 21:19
 */

@Service
public class SightIssueService {

    private ConcurrentMap<String, SightIssue> sightContainer = new ConcurrentHashMap<>();

    @Autowired
    private IStatisticsDao iStatisticsDao;

    @Async
    public void accumulateSightIssue(String issueid) {
        if (sightContainer.containsKey(issueid)) {
            SightIssue sightIssue = sightContainer.get(issueid);
            sightIssue.add();
            sightContainer.put(issueid, sightIssue);
        } else {
            sightContainer.put(issueid, new SightIssue(issueid, new AtomicInteger(1)));
        }
    }

    //每五分钟执行一次
    @Scheduled(cron = "0 0/5 * * * ? *")
    private void addDBSightIssue() {
        if (sightContainer.size() == 0) {
            return;
        }
        ConcurrentMap<String, SightIssue> temp = sightContainer;
        sightContainer.clear();
        Iterator<Map.Entry<String, SightIssue>> entryIterator = temp.entrySet().iterator();
        List<SightIssue> list = new ArrayList<>();
        while (entryIterator.hasNext()) {
            Map.Entry<String, SightIssue> stringSightIssueEntry = entryIterator.next();
            list.add(stringSightIssueEntry.getValue());
        }
        iStatisticsDao.updateSightIssue(list);
    }

}
