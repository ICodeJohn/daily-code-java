package com.opensource.module.huawei.od;

import java.util.*;

public class APISuccessRate_010 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int minAvg = sc.nextInt();  // 允许的平均失败率
        sc.nextLine();
        String[] arrStr = sc.nextLine().split(" ");
        int n = arrStr.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(arrStr[i]);

        // 计算前缀和，pre[i] 表示 arr[0..i-1] 的和
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + arr[i];

        int maxLen = 0;
        List<String> res = new ArrayList<>();

        // 枚举所有区间 [l, r]
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int sum = pre[r + 1] - pre[l];
                int len = r - l + 1;
                if (sum <= len * minAvg) {
                    if (len > maxLen) {
                        maxLen = len;
                        res.clear();
                        res.add(l + "-" + r);
                    } else if (len == maxLen) {
                        res.add(l + "-" + r);
                    }
                }
            }
        }

        if (res.isEmpty()) {
            System.out.println("NULL");
        } else {
            System.out.println(String.join(" ", res));
        }
    }

}
