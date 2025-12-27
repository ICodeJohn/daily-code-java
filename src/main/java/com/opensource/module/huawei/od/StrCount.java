package com.opensource.module.huawei.od;

import java.util.Scanner;

public class StrCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int k = sc.nextInt();
        int n = input.length();

        // 不满足最小长度要求
        if (n < 10 + k) {
            System.out.println(0);
            return;
        }

        int res = 0;                // 结果统计
        int[] digitCount = new int[10];  // 数字出现次数
        int numberCount = 0;        // 当前窗口中数字种类数
        int countChar = 0;          // 当前窗口中英文字母数量
        int left = 0;

        for (int right = 0; right < n; right++) {
            char c = input.charAt(right);
            if (Character.isLetter(c)) {
                countChar++;        // 字母计数+1
            }
            if (Character.isDigit(c)) {
                int d = c - '0';
                digitCount[d]++;
                if (digitCount[d] == 1) {
                    numberCount++;  // 新增数字种类
                }
            }

            // 超过k个字母时，移动左指针缩小窗口
            while (left <= right && countChar > k) {
                char leftChar = input.charAt(left);
                if (Character.isLetter(leftChar)) {
                    countChar--;
                }
                if (Character.isDigit(leftChar)) {
                    int d = leftChar - '0';
                    digitCount[d]--;
                    if (digitCount[d] == 0) {
                        numberCount--; // 减少数字种类
                    }
                }
                left++;
            }

            // 当数字种类达到10，字母数正好为k时，尝试移动左指针寻找更多合法子串
            if (numberCount == 10 && countChar == k) {
                // 复制计数数据，模拟移动左指针
                int[] tempCount = digitCount.clone();
                int tempNumberCount = numberCount;
                int tempCountChar = countChar;
                int tmpLeft = left;
                while (tmpLeft <= right && tempNumberCount == 10 && tempCountChar == k) {
                    res++;
                    char ch = input.charAt(tmpLeft);
                    if (Character.isLetter(ch)) {
                        tempCountChar--;
                    }
                    if (Character.isDigit(ch)) {
                        int d = ch - '0';
                        tempCount[d]--;
                        if (tempCount[d] == 0) {
                            tempNumberCount--;
                        }
                    }
                    tmpLeft++;
                }
            }
        }
        System.out.println(res);
    }
}
