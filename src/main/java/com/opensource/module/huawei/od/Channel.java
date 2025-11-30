package com.opensource.module.huawei.od;

import java.util.Scanner;

public class Channel {

    public static void main(String[] args) {
        System.out.println(-10/1);
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int[] cnt = new int[R + 1];  // 0~R 阶，共 R+1 个
        for (int i = 0; i <= R; i++) {
            cnt[i] = sc.nextInt();
        }
        int D = sc.nextInt();
        System.out.println(maxUsers(R, cnt, D));
    }

    // 主函数：最多可以满足多少用户
    public static int maxUsers(int R, int[] cnt, int D) {
        int users = 0;

        while (true) {
            int need = D;

            // 第 1 轮：按能整除的数量优先使用，从大阶到小阶
            for (int r = R; r >= 0; r--) {
                int size = 1 << r;
                int canUse = Math.min(cnt[r], need / size);
                need -= canUse * size;
                cnt[r] -= canUse;
            }

            // 第 2 轮：如果还不足，则继续从大阶往小阶，一次拿一个（不能浪费）
            for (int r = R; r >= 0 && need > 0; r--) {
                int size = 1 << r;
                while (cnt[r] > 0 && need > 0) {
                    cnt[r]--;
                    need -= size;
                }
            }

            // 如果最终 still need > 0，说明已经无法满足一个新用户
            if (need > 0) break;

            users++;
        }

        return users;
    }
}
