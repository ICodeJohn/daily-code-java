package com.opensource.module.huawei.od;

import java.util.*;

public class LessBall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long SUM = sc.nextLong();
        int N = sc.nextInt();

        int[] A = new int[N];
        long total = 0;
        int maxVal = 0;

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            total += A[i];
            maxVal = Math.max(maxVal, A[i]);
        }

        // 规则一
        if (total <= SUM) {
            System.out.println("[]");
            return;
        }

        // 二分查找 maxCapacity
        int left = 0, right = maxVal;
        int cap = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long curSum = 0;

            for (int x : A) {
                curSum += Math.min(x, mid);
            }

            if (curSum <= SUM) {
                cap = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 计算拿出的球
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = Math.max(0, A[i] - cap);
        }

        // 输出
        System.out.println(Arrays.toString(result));
    }
}