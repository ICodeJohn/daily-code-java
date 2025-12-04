package com.opensource.module.huawei.od;

import java.util.*;

public class PoSum_007 {

    static class PO {
        int id;
        int qty;
        int price; // 最终单价（已折扣或未折扣）

        PO(int id, int qty, int price) {
            this.id = id;
            this.qty = qty;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        // Map：低价（<=100）商品累加数量
        Map<Integer, int[]> lowPriceMap = new HashMap<>();
        // List：高价（>100）商品直接生成 PO
        List<PO> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int id = sc.nextInt();
            int qty = sc.nextInt();
            int price = sc.nextInt();
            int status = sc.nextInt();

            // 过滤非审批通过
            if (status != 0) continue;

            if (price > 100) {
                // 高价商品不合并
                result.add(new PO(id, qty, price));
            } else {
                // 低价商品合并
                lowPriceMap.putIfAbsent(id, new int[]{0, price});
                lowPriceMap.get(id)[0] += qty;  // 累加数量
            }
        }

        // 处理低价商品：折扣 + 加入 result
        for (Map.Entry<Integer, int[]> e : lowPriceMap.entrySet()) {
            int id = e.getKey();
            int qty = e.getValue()[0];
            int price = e.getValue()[1];

            int finalPrice = price;

            // 单价 < 100 才能打折，数量 >= 100
            if (price < 100 && qty >= 100) {
                finalPrice = (int) Math.ceil(price * 0.9);
            }

            result.add(new PO(id, qty, finalPrice));
        }

        // 排序：ID 升序，ID 相同则数量降序
        result.sort((a, b) -> {
            if (a.id != b.id) return a.id - b.id;
            return b.qty - a.qty;
        });

        // 输出
        for (PO po : result) {
            System.out.println(po.id + " " + po.qty + " " + po.price);
        }
    }

}
