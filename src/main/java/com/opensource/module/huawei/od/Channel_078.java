package com.opensource.module.huawei.od;

import java.util.Scanner;

public class Channel_078 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 最大阶数
        int R = sc.nextInt();

        // 各阶信道数量
        int[] cnt = new int[R + 1];
        for (int i = 0; i <= R; i++) {
            cnt[i] = sc.nextInt();
        }

        // 单个用户需要的数据量
        int D = sc.nextInt();

        long users = 0;

        // 无限尝试为用户分配，直到失败
        while (true) {
            long need = D;

            // 从高阶到低阶分配
            for (int r = R; r >= 0 && need > 0; r--) {
                while (cnt[r] > 0 && need > 0) {
                    cnt[r]--;
                    need -= (1L << r);
                }
            }

            // 如果当前用户没凑够 D，结束
            if (need > 0) {
                break;
            }

            users++;
        }

        System.out.println(users);
    }
}
