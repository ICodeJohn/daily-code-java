package com.opensource.module;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/31 15:04
 * @Version V1.0
 */
public class BaseLimiterImpl implements Limiter {

    private long limit;


    private Object lock = new Object();

    /**
     * 计数器
     */
    private AtomicInteger  counter = new AtomicInteger(0);



    public BaseLimiterImpl(Long limit) {
        this.limit = limit;
    }


    /**
     *
     * 这个只能实现普通限流，比如秒杀数不能超限等限制场景
     *
     * @return
     */

    @Override
    public boolean allow() {
        synchronized (lock){
            int cur = counter.incrementAndGet();
            if (cur > limit){
                counter.decrementAndGet();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
    }

    public static void main(String[] args) {
        Limiter limiter = new BaseLimiterImpl(100L);
        for (int i = 0; i < 101; i++) {
            System.out.println(limiter.allow());
        }
    }
}
