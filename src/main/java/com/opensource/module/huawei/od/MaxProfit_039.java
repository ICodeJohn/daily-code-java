package com.opensource.module.huawei.od;

import java.util.Scanner;

public class MaxProfit_039 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();  // 商品种类数
        int days = scanner.nextInt();    // 天数

        int[] limits = new int[number];  // 每种商品的最大持有量
        for (int i = 0; i < number; i++) {
            limits[i] = scanner.nextInt();
        }

        int[][] prices = new int[number][days];  // 每种商品每天的价格
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < days; j++) {
                prices[i][j] = scanner.nextInt();
            }
        }

        long totalProfit = 0;

        // 对每种商品独立计算最大利润
        for (int i = 0; i < number; i++) {
            totalProfit += calculateProfit(prices[i], limits[i]);
        }

        System.out.println(totalProfit);
        scanner.close();
    }

    // 计算单个商品的最大利润
    private static long calculateProfit(int[] prices, int limit) {
        int n = prices.length;
        long profit = 0;

        // 贪心：在价格低时买入，价格高时卖出
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += (long)(prices[i] - prices[i - 1]) * limit;
            }
        }

        return profit;
    }
}