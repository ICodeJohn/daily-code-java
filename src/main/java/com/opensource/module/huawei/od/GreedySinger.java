package com.opensource.module.huawei.od;

import java.util.Scanner;

public class GreedySinger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取 T 和 N
        int T = scanner.nextInt();
        int N = scanner.nextInt();

        // 读取路程时间
        int[] travel = new int[N + 1];
        int travelSum = 0;
        for (int i = 0; i <= N; i++) {
            travel[i] = scanner.nextInt();
            travelSum += travel[i];
        }

        // 剩余可卖唱的天数
        int R = T - travelSum;
        if (R < 0) {
            System.out.println(0);
            return;
        }

        // 读取每个城市的 M 和 D
        int[] M = new int[N];
        int[] D = new int[N];
        for (int i = 0; i < N; i++) {
            M[i] = scanner.nextInt();
            D[i] = scanner.nextInt();
        }

        // 预处理每个城市停留 k 天的收益
        int[][] profit = new int[N][R + 1];
        for (int i = 0; i < N; i++) {
            for (int k = 1; k <= R; k++) {
                // 第 j 天收益 = max(M[i] - (j-1)*D[i], 0)
                int dayProfit = M[i] - (k - 1) * D[i];
                if (dayProfit <= 0) {
                    // 如果某天收益<=0，则后面天数收益都是0，可以直接用之前的总收益
                    profit[i][k] = profit[i][k - 1];
                } else {
                    profit[i][k] = profit[i][k - 1] + dayProfit;
                }
            }
        }

        // 动态规划 dp[j] 表示用 j 天卖唱能获得的最大收益
        int[] dp = new int[R + 1];

        // 分组背包
        for (int i = 0; i < N; i++) {
            for (int j = R; j >= 0; j--) {
                for (int k = 1; k <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k] + profit[i][k]);
                }
            }
        }

        // 找最大收益
        int maxProfit = 0;
        for (int j = 0; j <= R; j++) {
            maxProfit = Math.max(maxProfit, dp[j]);
        }

        System.out.println(maxProfit);
    }
}
