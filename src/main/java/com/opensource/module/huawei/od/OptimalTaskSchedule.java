package com.opensource.module.huawei.od;

import java.util.*;

public class OptimalTaskSchedule {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(",");
        int N = sc.nextInt();

        // 统计频率
        Map<Integer, Integer> freq = new HashMap<>();
        for (String s : arr) {
            int val = Integer.parseInt(s.trim());
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }

        // 找最大频率
        int maxFreq = 0;
        for (int v : freq.values()) {
            maxFreq = Math.max(maxFreq, v);
        }

        // 找出现次数等于 maxFreq 的任务数量
        int maxCount = 0;
        for (int v : freq.values()) {
            if (v == maxFreq) maxCount++;
        }

        // 核心公式
        int frame = (maxFreq - 1) * (N + 1) + maxCount;

        int totalTasks = arr.length;
        int result = Math.max(frame, totalTasks);

        System.out.println(result);
    }
}
