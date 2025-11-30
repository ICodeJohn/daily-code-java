package com.opensource.module.huawei.od;

import java.util.*;

public class IntervalIntersection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] intervals = new int[n][2];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }

        // 1. 收集所有公共区间
        List<int[]> common = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int start = Math.max(intervals[i][0], intervals[j][0]);
                int end = Math.min(intervals[i][1], intervals[j][1]);
                if (start <= end) {
                    common.add(new int[]{start, end});
                }
            }
        }

        // 2. 如果没有公共区间
        if (common.isEmpty()) {
            System.out.println("None");
            return;
        }

        // 3. 按左端点排序
        common.sort(Comparator.comparingInt(a -> a[0]));

        // 4. 合并区间
        List<int[]> merged = new ArrayList<>();
        int[] current = common.get(0);
        for (int i = 1; i < common.size(); i++) {
            int[] next = common.get(i);
            if (next[0] <= current[1]) { // 重叠或相邻
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);

        // 5. 输出
        for (int[] interval : merged) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }
}
