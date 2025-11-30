package com.opensource.module.huawei.od;

import java.util.*;

public class CoverSubStr {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        int k = scanner.nextInt();

        int n1 = s1.length();
        int n2 = s2.length();
        int L = n1 + k;

        if (n2 < L) {
            System.out.println(-1);
            return;
        }

        // 统计 s1 的频率
        Map<Character, Integer> need = new HashMap<>();
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int valid = 0;

        // 初始化窗口 [0, L-1]
        for (int i = 0; i < L; i++) {
            char c = s2.charAt(i);
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) {
                valid++;
            }
        }

        if (valid == need.size()) {
            System.out.println(0);
            return;
        }

        // 滑动窗口
        for (int left = 1; left <= n2 - L; left++) {
            int right = left + L - 1;

            // 移除 left-1
            char out = s2.charAt(left - 1);
            if (need.containsKey(out) && window.get(out).equals(need.get(out))) {
                valid--;
            }
            window.put(out, window.get(out) - 1);

            // 加入 right
            char in = s2.charAt(right);
            window.put(in, window.getOrDefault(in, 0) + 1);
            if (need.containsKey(in) && window.get(in).equals(need.get(in))) {
                valid++;
            }

            if (valid == need.size()) {
                System.out.println(left);
                return;
            }
        }

        System.out.println(-1);
    }

}
