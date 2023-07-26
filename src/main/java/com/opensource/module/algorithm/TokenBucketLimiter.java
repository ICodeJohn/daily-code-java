package com.opensource.module.algorithm;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/23 16:03
 * @Version V1.0
 */
public class TokenBucketLimiter {

    private long capacity;
    private long allocRate;
    private long tokenSize;
    private long lastAllocTime;
    private Object lock;

    public TokenBucketLimiter(int capacity, int allocRate) {
        this.capacity = capacity;
        this.allocRate = allocRate;
        this.tokenSize = capacity;
        this.lastAllocTime = System.currentTimeMillis();
        lock = new Object();
    }

    public boolean allow() {
        synchronized (this.lock) {
            alloc();
            if (this.tokenSize > 0) {
                this.tokenSize--;
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    private void alloc() {
        long current = System.currentTimeMillis();
        double elapsedTime = (current - this.lastAllocTime) % 1000.0;
        long allocSize = (long) (elapsedTime * this.allocRate);
        if (allocSize > 0) {
            this.tokenSize = Math.min(allocSize + this.tokenSize, this.capacity);
            this.lastAllocTime = current;
        }
    }
}
