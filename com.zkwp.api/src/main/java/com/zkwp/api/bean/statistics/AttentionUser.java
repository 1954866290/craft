package com.zkwp.api.bean.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangkun
 * @date 2020/4/22 22:16
 **/
@Data
public class AttentionUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private AtomicInteger count;

    public AttentionUser() {

    }

    public AttentionUser(String userId, AtomicInteger count) {
        this.count = count;
        this.userId = userId;
    }

    public AttentionUser add() {
        this.count.incrementAndGet();
        return this;
    }

    public AttentionUser clear() {
        this.count.set(0);
        return this;
    }

    public int get() {
        return this.count.get();
    }
}
