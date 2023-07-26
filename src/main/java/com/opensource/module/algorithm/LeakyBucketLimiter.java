package com.opensource.module.algorithm;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/23 16:37
 * @Version V1.0
 */
public class LeakyBucketLimiter {
    private long capacity;
    private long tokenSize;
    private long leakyRate;
    private long lastLeakyTime;
    private Object lock;


    public LeakyBucketLimiter(long capacity, long tokenSize,long leakyRate) {
        this.capacity = capacity;
        this.tokenSize = tokenSize;
        this.leakyRate = leakyRate;
        this.lastLeakyTime = System.currentTimeMillis();
        this.lock = new Object();
    }

    public boolean allow() {
        synchronized (this.lock){
            leaky();
           if(this.tokenSize > 0){
               this.tokenSize--;
               return Boolean.TRUE;
           }
            return Boolean.FALSE;
        }
    }

    private void leaky(){
        long current = System.currentTimeMillis();
        long elapsedTime = current - this.lastLeakyTime;
        long leakyTotal = (long)(elapsedTime /1000.0 *leakyRate);
        if(this.tokenSize >= leakyTotal){
            this.tokenSize = 0;
        }else {
            this.tokenSize = leakyTotal- tokenSize;
        }
        this.lastLeakyTime = current;
    }
}
