package com.opensource.module.huawei.od;

import java.util.*;

public class StringSummary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // 1. 去除非字母并转小写
        StringBuilder cleanStr = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                cleanStr.append(Character.toLowerCase(c));
            }
        }
        String str = cleanStr.toString();

        // 2. 处理连续与非连续
        List<String> groups = new ArrayList<>();
        int n = str.length();
        int i = 0;

        while (i < n) {
            char currentChar = str.charAt(i);
            // 检查是否连续
            if (i < n - 1 && str.charAt(i + 1) == currentChar) {
                // 连续情况
                int count = 1;
                while (i + count < n && str.charAt(i + count) == currentChar) {
                    count++;
                }
                groups.add(currentChar + String.valueOf(count));
                i += count;
            } else {
                // 非连续情况
                int countLater = 0;
                for (int j = i + 1; j < n; j++) {
                    if (str.charAt(j) == currentChar) {
                        countLater++;
                    }
                }
                groups.add(currentChar + String.valueOf(countLater));
                i++;
            }
        }

        // 3. 排序
        groups.sort((a, b) -> {
            int numA = Integer.parseInt(a.substring(1));
            int numB = Integer.parseInt(b.substring(1));
            if (numA != numB) {
                return numB - numA; // 数字大的在前
            }
            return a.charAt(0) - b.charAt(0); // 字母小的在前
        });

        // 4. 输出
        StringBuilder result = new StringBuilder();
        for (String g : groups) {
            result.append(g);
        }
        System.out.println(result.toString());
    }

}
