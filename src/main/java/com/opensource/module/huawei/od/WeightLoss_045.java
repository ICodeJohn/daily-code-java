package com.opensource.module.huawei.od;

import java.util.Scanner;

public class WeightLoss_045 {

    static int n, t, k;
    static int[] calories;
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        t = scanner.nextInt();
        k = scanner.nextInt();
        calories = new int[n];
        for (int i = 0; i < n; i++) {
            calories[i] = scanner.nextInt();
        }

        dfs(0, 0, 0);
        System.out.println(count);
    }

    static void dfs(int index, int chosen, int sum) {
        if (chosen == k && sum == t) {
            count++;
            return;
        }
        if (index >= n || chosen > k || sum > t) {
            return;
        }
        // 选当前运动
        dfs(index + 1, chosen + 1, sum + calories[index]);
        // 不选当前运动
        dfs(index + 1, chosen, sum);
    }
}
