package com.opensource.module.huawei.od;

import java.util.*;

public class Connector {
    public static void main(String[] args) {
        // 示例输入处理（实际可能需要解析字符串）
        // 用例1
        int[][] intervals = {{1, 10}, {15, 20}, {18, 30}, {33, 40}};
        int[] connectors = {5, 4, 3, 2};

        // 用例2
        // int[][] intervals = {{1,2}, {3,5}, {7,10}, {15,20}, {30,100}};
        // int[] connectors = {5,4,3,2,1};

        // 1. 合并区间
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        int[] cur = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= cur[1]) {
                // 重叠或相邻，合并
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                merged.add(cur);
                cur = intervals[i];
            }
        }
        merged.add(cur);

        // 如果只有一个区间，不需要连接器
        if (merged.size() == 1) {
            System.out.println(1);
            return;
        }

        // 2. 计算间隙
        List<Integer> gaps = new ArrayList<>();
        for (int i = 1; i < merged.size(); i++) {
            int gap = merged.get(i)[0] - merged.get(i - 1)[1];
            gaps.add(gap);
        }

        // 3. 排序间隙和连接器
        Collections.sort(gaps);
        Arrays.sort(connectors);

        // 4. 贪心匹配
        int count = merged.size(); // 初始区间数
        int connIndex = 0;
        for (int gap : gaps) {
            // 找到能覆盖当前gap的最小连接器
            while (connIndex < connectors.length && connectors[connIndex] < gap) {
                connIndex++;
            }
            if (connIndex < connectors.length) {
                count--; // 使用连接器，区间数减1
                connIndex++;
            } else {
                break; // 没有可用连接器
            }
        }

        System.out.println(count);
    }
}
