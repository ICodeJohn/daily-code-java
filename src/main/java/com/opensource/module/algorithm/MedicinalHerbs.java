package com.opensource.module.algorithm;

import java.util.*;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/27 11:45
 * @Version V1.0
 */
public class MedicinalHerbs {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int N = scanner.nextInt();
        int[] times = new int[N];
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            int time = scanner.nextInt();
            int value = scanner.nextInt();
            times[i] = time;
            values[i] = value;
        }

        int[][] dp = new int[N + 1][T + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= T; j++) {
                if (j < times[i-1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], (dp[i-1][j-times[i-1]] + values[i-1]));
                }
            }
        }

        System.out.println(dp[N][T]);
        scanner.close();
    }
}
