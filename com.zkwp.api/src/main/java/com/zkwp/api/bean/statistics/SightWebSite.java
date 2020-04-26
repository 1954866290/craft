package com.zkwp.api.bean.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangkun
 * @date 2020/4/22 22:11
 **/
@Data
public class SightWebSite implements Serializable {
    private static final long serialVersionUID = 21L;
    private AtomicInteger count;
    private String calendar;

    public SightWebSite() {

    }

    public SightWebSite(String calendar, AtomicInteger count) {
        this.calendar = calendar;
        this.count = count;
    }

    public SightWebSite add() {
        this.count.incrementAndGet();
        return this;
    }

    public int get() {
        return this.count.get();
    }

    public SightWebSite clear() {
        this.count.set(0);
        return this;
    }
}
