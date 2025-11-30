package com.opensource.module.huawei.od;

import java.util.Scanner;

//一、题目描述
//小明在做构造数列的题目，题目要求数列中第一个数为n，且数列后面的每一个数字都不能大于前一个数字的一半，数列的元素是正整数，请问在给定n的情况下，最多能构造多少合适且不同的数列？
//
//二、输入描述
//输入一个n。
//
//备注：
//
//        1 ≤ n ≤ 10000
//
//三、输出描述
//        输出可以构造的序列个数
//
//四、测试用例
//测试用例1：
//        1、输入
//7
//
//        2、输出
//6
//
//        3、说明
//可以构成 [7], [7,3], [7,2], [7,1], [7,3,1], [7,2,1]
//
//测试用例2：
//        1、输入
//5
//
//        2、输出
//4
//
//        3、说明
//可以构成 [5], [5,2], [5,1], [5,2,1]
//这个题目在考察什么，解题思路


public class ConstructSequence {

//    static int[] memo;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        memo = new int[n + 1];
//        Arrays.fill(memo, -1);
//        System.out.println(dfs(n));
//    }
//
//    static int dfs(int x) {
//        if (x == 1) return 1;
//        if (memo[x] != -1) return memo[x];
//
//        int count = 1; // 序列 [x] 本身
//        for (int i = 1; i <= x / 2; i++) {
//            count += dfs(i);
//        }
//        memo[x] = count;
//        return count;
//    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp = new int[n + 1];
        //dp[n] = 1 + dp[1] +dp[2] +dp[floor[n/2]]
        for (int i = 1; i <= n; i++) {
            dp[i] = 1; // 序列 [i] 本身
            for (int j = 1; j <= i / 2; j++) {
                dp[i] += dp[j];
            }
        }

        System.out.println(dp[n]);
    }
}
