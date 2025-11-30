package com.opensource.module.huawei.od;

import java.util.*;

public class PerfectWindow {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(minReplacementLength(s));
        scanner.close();
    }

    public static int minReplacementLength(String s) {
        int n = s.length();
        if (n % 4 != 0) return -1; // 根据题意应该不会出现

        int target = n / 4;

        // 统计整个字符串中各个字符的数量
        Map<Character, Integer> totalCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            totalCount.put(c, totalCount.getOrDefault(c, 0) + 1);
        }

        // 如果已经是完美走位，返回0
        if (isPerfect(totalCount, target)) {
            return 0;
        }

        // 使用滑动窗口寻找最小替换长度
        int left = 0;
        int minLen = n;
        Map<Character, Integer> windowCount = new HashMap<>();

        for (int right = 0; right < n; right++) {
            // 右指针移动，增加字符到窗口
            char rightChar = s.charAt(right);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);

            // 当窗口外的字符数量都不超过target时，尝试收缩窗口
            while (left <= right && isValidWindow(totalCount, windowCount, target)) {
                minLen = Math.min(minLen, right - left + 1);

                // 左指针移动，从窗口移除字符
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                if (windowCount.get(leftChar) == 0) {
                    windowCount.remove(leftChar);
                }
                left++;
            }
        }

        return minLen;
    }

    // 检查是否是完美走位
    private static boolean isPerfect(Map<Character, Integer> count, int target) {
        return count.getOrDefault('W', 0) == target &&
                count.getOrDefault('A', 0) == target &&
                count.getOrDefault('S', 0) == target &&
                count.getOrDefault('D', 0) == target;
    }

    // 检查当前窗口是否有效（即窗口外的字符数量都不超过target）
    private static boolean isValidWindow(Map<Character, Integer> totalCount,
                                         Map<Character, Integer> windowCount, int target) {
        for (char c : new char[]{'W', 'A', 'S', 'D'}) {
            int outsideCount = totalCount.getOrDefault(c, 0) - windowCount.getOrDefault(c, 0);
            if (outsideCount > target) {
                return false;
            }
        }
        return true;
    }
}
