package com.opensource.module.huawei.od;

import java.util.*;

public class RetrieveBall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int SUM = sc.nextInt();
        int N = sc.nextInt();

        long[] bucketBallNums = new long[N];
        long total = 0;
        long maxVal = 0;

        for (int i = 0; i < N; i++) {
            bucketBallNums[i] = sc.nextLong();
            total += bucketBallNums[i];
            maxVal = Math.max(maxVal, bucketBallNums[i]);
        }

        // 规则一：总和不超过 SUM
        if (total <= SUM) {
            System.out.println("[]");
            return;
        }

        // 规则二：二分查找 maxCapacity
        long left = 0, right = maxVal;
        long maxCapacity = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.min(bucketBallNums[i], mid);
            }
            if (sum <= SUM) {
                maxCapacity = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 计算每个桶拿出的球数
        long[] result = new long[N];
        for (int i = 0; i < N; i++) {
            result[i] = Math.max(0, bucketBallNums[i] - maxCapacity);
        }

        // 输出格式 [0,1,0,3,3,0,2]
        System.out.print("[");
        for (int i = 0; i < N; i++) {
            System.out.print(result[i]);
            if (i < N - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
