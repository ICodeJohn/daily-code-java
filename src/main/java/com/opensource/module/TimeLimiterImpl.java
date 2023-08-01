package com.opensource.module;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: "根据QPS限流"
 * @Description: 设计思路
 * 1）设置一个计数器，不断根据时间戳流失增加qps阈值，这个阈值可能需要分段到较小粒度
 * ，比如说0.01秒释放一定比率的调用量。
 * <p>
 * 2）设置一个数量一定的HashMap，size 不大于60，每个key保留当前一秒的调用量。这样可以避免
 * 第一种方案中切换不同秒时的控制问题。本例选择第2种方案。
 * @Author: ZhaoWei
 * @Date: 2023/7/31 15:21
 * @Version V1.0
 */
public class TimeLimiterImpl implements Limiter {

    /**
     * qps设置
     */
    private long limit;


    private Object lock = new Object();

    /**
     * 计数器
     */
    ConcurrentHashMap<Long, AtomicInteger> limiterMap = new ConcurrentHashMap();

    public TimeLimiterImpl(long limit) {
        this.limit = limit;
    }


    @Override
    public boolean allow() {
        synchronized (lock) {
            long second = System.currentTimeMillis() / 1000;
            AtomicInteger counter = limiterMap.get(second);
            if(Objects.isNull(counter)){
                counter =  new AtomicInteger(0);
            }
            clearMap(limiterMap, second);
            counter.incrementAndGet();
            limiterMap.put(second,counter);
            if (counter.get() > limit) {
                counter.decrementAndGet();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
    }


    /**
     * 清除过期配置项设置
     *
     * @param limiterMap
     */
    private void clearMap(ConcurrentHashMap<Long, AtomicInteger> limiterMap, long second) {
        if(limiterMap.size() > 0 ){
            for (Map.Entry<Long, AtomicInteger> entry : limiterMap.entrySet()) {
                if (entry.getKey() < second) {
                    System.out.println("remove---" + entry.getKey());
                    limiterMap.remove(entry.getKey());
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        TimeLimiterImpl timeLimiter = new TimeLimiterImpl(5);
        for (int i = 0; i < 10; i++) {
            System.out.println(timeLimiter.allow());
        }
        Thread.sleep(2000);
        for (int i = 0; i < 10; i++) {
            System.out.println(timeLimiter.allow());
        }
    }
}
