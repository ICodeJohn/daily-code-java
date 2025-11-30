package com.opensource.module.huawei.od;

import java.util.Scanner;

public class IlluminationRadius {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取路灯数量
        int N = scanner.nextInt();

        // 读取照明半径
        int[] radii = new int[N];
        for (int i = 0; i < N; i++) {
            radii[i] = scanner.nextInt();
        }

        // 计算无法照明的区间总长度
        long totalDark = 0;
        for (int i = 1; i < N; i++) {
            int p1 = (i - 1) * 100;      // 前一个路灯位置
            int p2 = i * 100;            // 后一个路灯位置
            int R1 = p1 + radii[i - 1];  // 前一个路灯照明右边界
            int L2 = p2 - radii[i];      // 后一个路灯照明左边界

            // 如果存在未覆盖区间
            if (R1 < L2) {
                totalDark += (L2 - R1);
            }
        }

        System.out.println(totalDark);

        scanner.close();
    }
}
