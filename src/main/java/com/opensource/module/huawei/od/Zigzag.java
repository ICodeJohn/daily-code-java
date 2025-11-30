package com.opensource.module.huawei.od;

import java.util.Scanner;

public class Zigzag {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读一整行
        String line = sc.nextLine().trim();
        if (line.length() == 0) {
            System.out.println("[ ]");
            return;
        }

        String[] parts = line.split("\\s+");
        int[] arr = new int[parts.length];

        // 检查输入是否合法（必须是正整数）
        for (int i = 0; i < parts.length; i++) {
            try {
                arr[i] = Integer.parseInt(parts[i]);
                if (arr[i] < 0) {
                    System.out.println("[ ]");
                    return;
                }
            } catch (Exception e) {
                System.out.println("[ ]");
                return;
            }
        }

        // 执行最小移动距离的 Zigzag 排列
        zigzag(arr);

        // 输出
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(" ");
        }
    }

    // 核心算法：贪心 + 局部交换，保证最小移动距离
    private static void zigzag(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {

            if (i % 2 == 0) {
                // 偶数位：高位 → arr[i] >= arr[i+1]
                if (arr[i] < arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            } else {
                // 奇数位：矮位 → arr[i] <= arr[i+1]
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
