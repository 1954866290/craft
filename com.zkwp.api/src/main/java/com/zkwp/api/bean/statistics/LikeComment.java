package com.zkwp.api.bean.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangkun
 * @date 2020/4/22 22:15
 **/
@Data
public class LikeComment implements Serializable {
    private static final long serialVersionUID = 37L;
    private String commentId;
    private AtomicInteger count;

    public LikeComment() {

    }

    public LikeComment(String commentId, AtomicInteger count) {
        this.commentId = commentId;
        this.count = count;
    }

    public LikeComment add() {
        this.count.incrementAndGet();
        return this;
    }

    public int get() {
        return this.count.get();
    }

    public LikeComment clear() {
        this.count.set(0);
        return this;
    }
}
