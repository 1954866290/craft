package com.zkwp.chat.service.statistics;

import com.zkwp.api.bean.statistics.SightWebSite;
import com.zkwp.chat.dao.statistics.IStatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * @auther zhangkun
 * @date 2020/4/25 13:03
 */

@Service
public class SightWebSiteService {

    private volatile SightWebSite sightWebSite = new SightWebSite();

    @Autowired
    IStatisticsDao iStatisticsDao;

    @Async
    public void accumulateSightWebSite() {
        sightWebSite.add();
    }

    @Scheduled(cron = "0 0/5 * * * ? *")
    private void addDBSightWebSite() {
        SightWebSite temp = sightWebSite;

        temp.setCalendar(getCalendar());
        iStatisticsDao.updateSightWebSite(temp);
    }

    private String getCalendar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-DD");
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }

    @Scheduled(cron = "1 0 0 1/1 * ?")
    private void insertSightWebSiteCalendar() {
        iStatisticsDao.insertSightWebSiteCalendar(new SightWebSite(getCalendar(), new AtomicInteger(0)));
    }

}
