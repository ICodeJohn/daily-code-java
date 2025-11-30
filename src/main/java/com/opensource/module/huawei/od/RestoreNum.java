package com.opensource.module.huawei.od;

import java.util.*;

public class RestoreNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int len = sc.nextInt();

        // 统计输入字符串的字符频次
        int[] inputFreq = new int[10];
        for (char c : s.toCharArray()) {
            inputFreq[c - '0']++;
        }

        // 枚举起始数字
        for (int start = 1; start <= 1000; start++) {
            // 生成连续序列
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(start + i);
            }
            String combined = sb.toString();

            // 如果长度不匹配，直接跳过
            if (combined.length() != s.length()) {
                continue;
            }

            // 统计生成字符串的字符频次
            int[] genFreq = new int[10];
            for (char c : combined.toCharArray()) {
                genFreq[c - '0']++;
            }

            // 比较频次
            if (Arrays.equals(inputFreq, genFreq)) {
                System.out.println(start);
                return;
            }
        }
    }
}
