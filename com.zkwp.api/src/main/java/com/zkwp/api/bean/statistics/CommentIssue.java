package com.zkwp.api.bean.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangkun
 * @date 2020/4/22 22:12
 **/
@Data
public class CommentIssue implements Serializable {
    private static final long serialVersionUID = 23L;
    private String issueId;
    private AtomicInteger count;

    public CommentIssue() {

    }

    public CommentIssue(String issueId, AtomicInteger count) {
        this.issueId = issueId;
        this.count = count;
    }

    public CommentIssue add() {
        this.count.incrementAndGet();
        return this;
    }

    public int get() {
        return this.count.get();
    }

    public CommentIssue clear() {
        this.count.set(0);
        return this;
    }

}
