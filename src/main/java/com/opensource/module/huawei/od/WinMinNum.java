package com.opensource.module.huawei.od;

import java.util.Scanner;

public class WinMinNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();   // 连续营地数量（窗口大小）
        int L = sc.nextInt();   // 命令数量

        int[] a = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            a[i] = sc.nextInt();
        }

        for (int t = 0; t < L; t++) {
            String cmd = sc.next();

            if (cmd.equals("Add")) {
                int i = sc.nextInt();
                int j = sc.nextInt();
                a[i] += j;   // 增加

            } else if (cmd.equals("Sub")) {
                int i = sc.nextInt();
                int j = sc.nextInt();
                a[i] -= j;   // 减少

            } else if (cmd.equals("Query")) {
                int i = sc.nextInt();  // query left
                int j = sc.nextInt();  // query right

                // 滑动窗口求最小连续 K 和
                int currentSum = 0;

                // 先计算第一个窗口
                for (int x = i; x < i + K; x++) {
                    currentSum += a[x];
                }

                int minSum = currentSum;

                // 滑动窗口
                for (int left = i + 1; left + K - 1 <= j; left++) {
                    currentSum = currentSum - a[left - 1] + a[left + K - 1];
                    if (currentSum < minSum) {
                        minSum = currentSum;
                    }
                }

                System.out.println(minSum);
            }
        }
    }
}
