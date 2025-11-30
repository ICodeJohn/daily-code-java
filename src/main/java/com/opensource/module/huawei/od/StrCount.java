package com.opensource.module.huawei.od;

import java.util.Scanner;

public class StrCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int k = sc.nextInt();

        int n = str.length();
        int[] digitCount = new int[10];
        int letterCount = 0;
        int totalDigits = 0; // 不同数字的个数

        long ans = 0;

        // 双指针
        int right = 0;
        for (int left = 0; left < n; left++) {
            // 移动右指针直到字母数 = k 且数字全，或者到末尾
            while (right < n && (letterCount < k || totalDigits < 10)) {
                char c = str.charAt(right);
                if (Character.isLetter(c)) {
                    letterCount++;
                } else {
                    int d = c - '0';
                    digitCount[d]++;
                    if (digitCount[d] == 1) {
                        totalDigits++;
                    }
                }
                right++;
            }

            // 如果满足条件
            if (letterCount == k && totalDigits == 10) {
                // 计算能延伸的长度（只加数字）
                int extend = 0;
                int temp = right;
                while (temp < n && Character.isDigit(str.charAt(temp))) {
                    extend++;
                    temp++;
                }
                ans += extend + 1;
            }

            // 移动左指针前，更新计数
            char leftChar = str.charAt(left);
            if (Character.isLetter(leftChar)) {
                letterCount--;
            } else {
                int d = leftChar - '0';
                digitCount[d]--;
                if (digitCount[d] == 0) {
                    totalDigits--;
                }
            }
        }

        System.out.println(ans);
    }
}
