package com.opensource.module.algorithm;

import java.util.*;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/19 09:19
 * @Version V1.0
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {

        /**initialCapacity：是初始数组长度
         * loadFactor：负载因子，表示数组的元素数量/数组长度超过这个比例，数组就要扩容
         * accessOrder：false： 基于插入顺序(默认), true: 基于访问顺序
         * 当accessOrder为true，每次get元素的时候，都会去执行afterNodeAccess 方法，这个方法会将元素重新插入到双向链表的结尾。
         */
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public Integer get(Object key) {
        /**
         * 这个getOrDefault 还是很好用的
         */
        return super.getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.get(1);
        lruCache.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        System.out.println("**********************************");
        Map<Integer, Integer> tree = new TreeMap(Comparator.reverseOrder());
        tree.put(3, 3);
        tree.put(2, 2);
        tree.put(1, 1);
        tree.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }
}
