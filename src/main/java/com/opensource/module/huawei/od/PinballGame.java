package com.opensource.module.huawei.od;

import java.util.*;

public class PinballGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        // 若 N < 3，不需要拆
        if (N < 3) {
            System.out.println(0 + " " + 1);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(N);

        int splitCount = 0; // 拆分次数

        while (true) {
            int size = q.size();
            boolean needContinue = false;

            // 临时保存本轮拆分结果
            List<Integer> next = new ArrayList<>();

            while (!q.isEmpty()) {
                int x = q.poll();
                if (x <= 2) {
                    next.add(x);
                } else {
                    // 拆分
                    int a = x / 2;
                    int b = x - a;

                    next.add(a);
                    next.add(b);

                    splitCount++;
                    needContinue = true;
                }
            }

            // 复制回队列
            q.addAll(next);

            // 如果没有任何新的拆分，则结束
            if (!needContinue) break;
        }

        // 最终份数 = 队列大小
        System.out.println(splitCount + " " + q.size());
    }
}
