package com.opensource.module.huawei.od;

import java.util.*;

public class SchoolSite {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = scanner.nextInt();
        }

        Arrays.sort(positions);

        // 中位数位置
        int medianIndex = (n - 1) / 2;
        System.out.println(positions[medianIndex]);

        scanner.close();
    }
}
