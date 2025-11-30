package com.opensource.module.huawei.od;

import java.util.*;

public class investmentIncome {
    static class Product {
        int e; // 收益率 %
        int r; // 风险等级

        public Product(int e, int r) {
            this.e = e;
            this.r = r;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long m = sc.nextLong(); // 可用资金 (万元)
        int n = sc.nextInt();   // 产品数量
        int x = sc.nextInt();   // 风险阈值
        long y = sc.nextLong(); // 单个产品最多可投金额 (万元)

        List<Product> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int e = sc.nextInt();
            int r = sc.nextInt();
            if (r <= x) {
                list.add(new Product(e, r));
            }
        }

        sc.close();

        // 如果没有合法产品
        if (list.isEmpty() || m <= 0) {
            System.out.println(0);
            return;
        }

        // 按收益率从大到小排序
        list.sort((a, b) -> b.e - a.e);

        double totalProfit = 0.0;
        long remain = m; // 剩余可投金额

        for (Product p : list) {
            if (remain <= 0) break;

            long invest = Math.min(y, remain); // 对该产品的投入金额
            totalProfit += invest * (p.e / 100.0);
            remain -= invest;
        }

        // 四舍五入取整
        long result = Math.round(totalProfit);
        System.out.println(result);
    }
}
