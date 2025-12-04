package com.opensource.module.huawei.od;

import java.util.*;

public class MostMVP_002 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] p = new int[t];
        for (int i = 0; i < t; i++) p[i] =sc.nextInt();

        System.out.println(minMvpScore(p));
    }

    // 主函数
    public static int minMvpScore(int[] p) {
        int n = p.length;
        int total = 0;
        for (int x : p) total += x;

        Arrays.sort(p);
        reverse(p); // 降序排序，大数优先放

        int maxP = p[0];

        // 尝试从最大可能人数 n 开始枚举
        for (int k = n; k >= 1; k--) {
            if (total % k != 0) continue;
            int S = total / k;  // 每个选手的得分

            if (S < maxP) continue; // 单个得分不能大于 S

            int[] buckets = new int[k]; // k 个选手的得分桶

            if (dfs(p, buckets, 0, S)) {
                return S;   // 找到了最大 k 时对应的 S
            }
        }
        return total; // 只有1人情况
    }

    // 回溯：尝试将 p[idx] 放入某个桶
    private static boolean dfs(int[] p, int[] buckets, int idx, int target) {
        if (idx == p.length) return true; // 所有数字放完

        int v = p[idx];
        int k = buckets.length;
        boolean[] used = new boolean[target + 1]; // 防止同和桶重复尝试

        for (int i = 0; i < k; i++) {
            // 若加入该桶不会超过 target
            if (buckets[i] + v <= target && !used[buckets[i]]) {

                used[buckets[i]] = true; // 记录该“当前和”已尝试过（对称剪枝）

                buckets[i] += v;
                if (dfs(p, buckets, idx + 1, target)) return true;
                buckets[i] -= v;
            }

            // 空桶剪枝：如果该桶是空的并且放不进去，那么其他空桶也一样没必要再试
            if (buckets[i] == 0) break;
        }
        return false;
    }

    // 工具函数：数组逆序
    private static void reverse(int[] a) {
        for (int l = 0, r = a.length - 1; l < r; l++, r--) {
            int tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
        }
    }
}
