package com.opensource.module.huawei.od;

import java.util.*;

public class StringConcat_B {

    static int n; // 目标长度
    static int[] counts = new int[26]; // 字符频次
    static long totalCount = 0; // 满足条件的排列数

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        sc.close();

        if (line.isEmpty()) {
            System.out.println(0);
            return;
        }

        int lastSpace = line.lastIndexOf(' ');
        if (lastSpace == -1) {
            System.out.println(0);
            return;
        }

        String chars = line.substring(0, lastSpace).trim();
        String nStr = line.substring(lastSpace + 1).trim();

        try {
            n = Integer.parseInt(nStr);
        } catch (Exception e) {
            System.out.println(0);
            return;
        }

        if (n <= 0 || n > 5 || chars.length() < n) {
            System.out.println(0);
            return;
        }

        // 统计字符频次
        for (char ch : chars.toCharArray()) {
            if (ch < 'a' || ch > 'z') {
                System.out.println(0);
                return;
            }
            counts[ch - 'a']++;
        }

        dfs(0, -1); // 当前长度为0，上一个字符不存在用 -1
        System.out.println(totalCount);
    }

    // pos: 当前长度, last: 上一个字符索引 (-1表示无)
    static void dfs(int pos, int last) {
        if (pos == n) {
            totalCount++;
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] == 0 || i == last) continue;
            counts[i]--;
            dfs(pos + 1, i);
            counts[i]++; // 回溯
        }
    }
}
