package com.opensource.module.huawei.od;

import java.util.*;

public class VirtualFinancial_046 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();   // 产品数
        int N = sc.nextInt();   // 总投资额
        int X = sc.nextInt();   // 最大风险

        int[] rate = new int[m];
        int[] risk = new int[m];
        int[] maxInvest = new int[m];

        for (int i = 0; i < m; i++) rate[i] = sc.nextInt();
        for (int i = 0; i < m; i++) risk[i] = sc.nextInt();
        for (int i = 0; i < m; i++) maxInvest[i] = sc.nextInt();

        int[] bestAlloc = new int[m];
        long bestValue = -1;

        // ---------- 情况一：只选 1 个产品 ----------
        for (int i = 0; i < m; i++) {
            if (risk[i] <= X) {
                int[] alloc = new int[m];
                int invest = Math.min(N, maxInvest[i]);
                alloc[i] = invest;

                long value = (long) invest * rate[i];
                if (value > bestValue) {
                    bestValue = value;
                    bestAlloc = alloc;
                }
            }
        }

        // ---------- 情况二：选 2 个产品 ----------
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (risk[i] + risk[j] <= X) {

                    // 按回报率倒序投钱（先投收益高的）
                    int p = i, q = j;
                    if (rate[q] > rate[p]) {
                        p = j;
                        q = i;
                    }

                    int[] alloc = new int[m];
                    int rem = N;

                    // 先投收益高的 p
                    alloc[p] = Math.min(maxInvest[p], rem);
                    rem -= alloc[p];

                    // 再投收益低的 q
                    alloc[q] = Math.min(maxInvest[q], rem);

                    long value = (long) alloc[p] * rate[p] +
                            (long) alloc[q] * rate[q];

                    if (value > bestValue) {
                        bestValue = value;
                        bestAlloc = alloc;
                    }
                }
            }
        }

        // ---------- 输出 ----------
        for (int i = 0; i < m; i++) {
            System.out.print(bestAlloc[i]);
            if (i < m - 1) System.out.print(" ");
        }
    }
}
