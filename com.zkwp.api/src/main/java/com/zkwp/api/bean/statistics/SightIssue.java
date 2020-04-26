package com.zkwp.api.bean.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangkun
 * @date 2020/4/22 22:04
 **/
@Data
public class SightIssue implements Serializable {
    private static final long serialVersionUID = 19L;
    private String issueId;
    private AtomicInteger count;

    public SightIssue() {

    }

    public SightIssue(String issueId, AtomicInteger count) {
        this.issueId = issueId;
        this.count = count;
    }

    public SightIssue add() {
        this.count.incrementAndGet();
        return this;
    }

    public int get() {
        return this.count.get();
    }

    public SightIssue clear() {
        this.count.set(0);
        return this;
    }
}
