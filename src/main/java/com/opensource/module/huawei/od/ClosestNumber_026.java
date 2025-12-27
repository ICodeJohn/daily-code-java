package com.opensource.module.huawei.od;

import java.util.Arrays;

public class ClosestNumber_026 {

    public static void main(String[] args) {
        // 示例输入 [50,50,2,3],2
        int[] X = {50, 50, 2, 3};
        int K = 2;

        int n = X.length;
        // 计算中位数
        int[] sortedX = X.clone();
        Arrays.sort(sortedX);
        int median = sortedX[n / 2];

        int bestIndex = -1;
        int minDiff = Integer.MAX_VALUE;

        // 遍历每个 i
        for (int i = 0; i <= n - K; i++) {
            int sum = X[i];
            for (int j = 1; j < K; j++) {
                sum -= X[i + j];
            }
            int diff = Math.abs(sum - median);
            // 更接近，或者一样接近但 i 更大
            if (diff < minDiff || (diff == minDiff && i > bestIndex)) {
                minDiff = diff;
                bestIndex = i;
            }
        }

        System.out.println(bestIndex);
    }
}
