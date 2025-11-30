package com.opensource.module.huawei.od;

import java.util.Arrays;
import java.util.Scanner;

public class Bicycle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        Arrays.sort(weights);

        int left = 0, right = n - 1;
        int count = 0;

        //== 是因为只剩一个人时候也可以被处理
        while (left <= right) {
            if (weights[left] + weights[right] <= m) {
                // 最轻和最重可以一起骑
                left++;
                right--;
            } else {
                // 最重的单独骑
                right--;
            }
            count++;
        }

        System.out.println(count);
    }
}
