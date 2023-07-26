package com.opensource.module.algorithm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/23 15:49
 * @Version V1.0
 */
public class CounterLimiter {

    private int limit;
    private AtomicInteger counter;

    public CounterLimiter(int limit) {
        this.limit = limit;
        counter = new AtomicInteger(0);
    }

    public boolean allow() {
        counter.incrementAndGet();
        if (counter.get() > limit) {
            counter.decrementAndGet();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
