package com.opensource.module.huawei.od;

import java.util.Scanner;

public class StrMatch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String haystack = scanner.nextLine();
        String needle = scanner.nextLine();
        System.out.println(enhancedStrstr(haystack, needle));
    }

    public static int enhancedStrstr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i < n; i++) {
            int j = 0; // needle指针
            int k = i; // haystack指针
            boolean match = true;

            while (j < m && k < n) {
                if (needle.charAt(j) == '[') {
                    // 找到匹配的']'
                    int endBracket = needle.indexOf(']', j);
                    if (endBracket == -1) {
                        match = false;
                        break;
                    }

                    String chars = needle.substring(j + 1, endBracket);
                    if (chars.indexOf(haystack.charAt(k)) == -1) {
                        match = false;
                        break;
                    }

                    // 匹配成功，跳过[]段
                    k++;
                    j = endBracket + 1;
                } else {
                    if (needle.charAt(j) != haystack.charAt(k)) {
                        match = false;
                        break;
                    }
                    j++;
                    k++;
                }
            }

            // 如果匹配成功且needle全部匹配完
            if (match && j == m) {
                return i;
            }
        }

        return -1;
    }
}
