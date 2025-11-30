package com.opensource.module.huawei.od;

import java.util.*;

public class StringConcat {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            System.out.println(0);
            return;
        }
        String line = sc.nextLine().trim();
        sc.close();
        if (line.isEmpty()) {
            System.out.println(0);
            return;
        }

        // 找到最后一个空格，左边为字符列表，右边为 N
        int lastSpace = line.lastIndexOf(' ');
        if (lastSpace == -1) {
            System.out.println(0);
            return;
        }
        String chars = line.substring(0, lastSpace).trim();
        String nStr = line.substring(lastSpace + 1).trim();

        int N;
        try {
            N = Integer.parseInt(nStr);
        } catch (NumberFormatException e) {
            System.out.println(0);
            return;
        }

        // 基本合法性检查
        if (N <= 0 || N > 5) {
            System.out.println(0);
            return;
        }
        if (chars.length() == 0) {
            System.out.println(0);
            return;
        }

        // 统计字符频次（仅允许 a-z）
        int[] counts = new int[26];
        for (char ch : chars.toCharArray()) {
            if (ch < 'a' || ch > 'z') {
                System.out.println(0);
                return;
            }
            counts[ch - 'a']++;
        }

        int totalAvailable = chars.length();
        if (N > totalAvailable) {
            System.out.println(0);
            return;
        }

        // DFS 回溯计数
        long result = dfsCount(N, counts, -1, 0);
        System.out.println(result);
    }

    /**
     * @param targetLen  目标长度 N
     * @param counts     当前每个字母剩余次数
     * @param last       上一次选择的字母索引（0..25），-1 表示没有（起始）
     * @param currentLen 当前已选长度
     * @return 满足条件的序列数
     */
    private static long dfsCount(int targetLen, int[] counts, int last, int currentLen) {
        if (currentLen == targetLen) return 1L;

        long sum = 0L;
        int remainingNeeded = targetLen - currentLen;

        // 简单剪枝：统计剩余可用字符总数
        int availableTotal = 0;
        int distinctAvailable = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                availableTotal += counts[i];
                distinctAvailable++;
            }
        }
        if (availableTotal < remainingNeeded) return 0L; // 不够字符

        // 如果剩余不同字符数量为1 且这个字符就是 last，则无法避免相邻相同
        if (distinctAvailable == 1) {
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 0) {
                    if (i == last && remainingNeeded > 1) return 0L;
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] == 0) continue;
            if (i == last) continue; // 不允许与前一个相同
            counts[i]--;
            sum += dfsCount(targetLen, counts, i, currentLen + 1);
            counts[i]++; // 回溯
        }
        return sum;
    }
}

