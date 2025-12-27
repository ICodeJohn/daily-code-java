package com.opensource.module.huawei.od;

import java.util.Scanner;

public class GameGroup_050 {

    static long total;
    static long minDiff;
    static int[] arr;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        n = input.length;
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        total = 0;
        for (int num : arr) {
            total += num;
        }

        minDiff = Long.MAX_VALUE;

        // 枚举所有选择n/2个元素的组合
        combine(0, 0, 0);

        System.out.println(minDiff);
    }

    // 递归枚举组合
    static void combine(int start, int count, long sum) {
        if (count == n / 2) {
            long diff = Math.abs(total - 2 * sum);
            if (diff < minDiff) {
                minDiff = diff;
            }
            return;
        }

        for (int i = start; i < n; i++) {
            combine(i + 1, count + 1, sum + arr[i]);
        }
    }
}
