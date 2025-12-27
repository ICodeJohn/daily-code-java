package com.opensource.module.huawei.od;


import java.util.Scanner;

public class MonkeyJump_022 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N <= 2) {
            System.out.println(1);
            return;
        }

        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }

        System.out.println(dp[N]);
    }
}

