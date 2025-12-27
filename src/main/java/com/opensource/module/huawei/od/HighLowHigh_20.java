package com.opensource.module.huawei.od;

import java.util.*;

public class HighLowHigh_20 {

    // 解析字符串到 int
    public static int parseStringToInt(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                res = res * 10 + (c - '0');
            } else {
                return -1; // 非数字字符，返回 -1
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> ans = new ArrayList<>();

        while (scanner.hasNext()) {
            String s = scanner.next();
            int tmp = parseStringToInt(s);
            if (tmp == -1) {
                System.out.println("[ ]");
                scanner.close();
                return;
            }
            ans.add(tmp);
        }
        scanner.close();

        // 贪心算法
        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i) > ans.get(i - 1) && (i % 2 == 1)) {
                Collections.swap(ans, i, i - 1);
            }
            if (ans.get(i) < ans.get(i - 1) && (i % 2 == 0)) {
                Collections.swap(ans, i, i - 1);
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(ans.get(i));
        }
    }
}
