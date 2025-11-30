package com.opensource.module.huawei.od;

import java.util.*;

public class VirtualFinancial {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();     // 产品数
        int N = sc.nextInt();     // 总投资额
        int X = sc.nextInt();     // 最大风险

        int[] rates = new int[m];
        int[] risks = new int[m];
        int[] maxInvest = new int[m];

        for (int i = 0; i < m; i++) rates[i] = sc.nextInt();
        for (int i = 0; i < m; i++) risks[i] = sc.nextInt();
        for (int i = 0; i < m; i++) maxInvest[i] = sc.nextInt();

        int[] bestAlloc = new int[m];
        long bestReturn = -1;

        // ---------- 情况1：只投一个产品 ----------
        for (int i = 0; i < m; i++) {
            if (risks[i] <= X) {
                int invest = Math.min(maxInvest[i], N);
                long ret = (long) invest * rates[i];
                if (ret > bestReturn) {
                    bestReturn = ret;
                    Arrays.fill(bestAlloc, 0);
                    bestAlloc[i] = invest;
                }
            }
        }

        // ---------- 情况2：投两个产品 ----------
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {

                if (risks[i] + risks[j] <= X) {

                    // 按回报率从高到低排序：a 高回报，b 次之
                    int a, b;
                    if (rates[i] >= rates[j]) {
                        a = i;
                        b = j;
                    } else {
                        a = j;
                        b = i;
                    }

                    // 先把钱投给回报高的
                    int investA = Math.min(maxInvest[a], N);
                    int remain = N - investA;

                    // 再投剩下的钱给另一种
                    int investB = Math.min(maxInvest[b], remain);

                    long ret = (long) investA * rates[a] + (long) investB * rates[b];

                    if (ret > bestReturn) {
                        bestReturn = ret;
                        Arrays.fill(bestAlloc, 0);
                        bestAlloc[a] = investA;
                        bestAlloc[b] = investB;
                    }
                }
            }
        }

        // ---------- 若根本无法投资（风险太高），输出 0 0 ... ----------
        if (bestReturn < 0) {
            Arrays.fill(bestAlloc, 0);
        }

        // ---------- 输出 ----------
        for (int i = 0; i < m; i++) {
            System.out.print(bestAlloc[i]);
            if (i != m - 1) System.out.print(" ");
        }
    }
}
