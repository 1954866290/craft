package com.zkwp.api.bean.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangkun
 * @date 2020/4/22 22:13
 **/
@Data
public class LikeIssue implements Serializable {
    private static final long serialVersionUID = 29L;
    private String issueId;
    private AtomicInteger count;

    public LikeIssue() {

    }

    public LikeIssue(String issueId, AtomicInteger count) {
        this.count = count;
        this.issueId = issueId;
    }

    public LikeIssue add() {
        this.count.incrementAndGet();
        return this;
    }

    public int get() {
        return this.count.get();
    }

    public LikeIssue clear() {
        this.count.set(0);
        return this;
    }
}
