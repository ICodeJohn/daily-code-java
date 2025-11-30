package com.opensource.module.huawei.od;

import java.util.Scanner;

public class StrReplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(replaceUnderscore(input));
    }

    public static String replaceUnderscore(String s) {
        StringBuilder sb = new StringBuilder();
        int bracketLevel = 0;
        int i = 0;
        int n = s.length();

        while (i < n) {
            char c = s.charAt(i);

            // 处理转义
            if (c == '\\' && i + 1 < n && s.charAt(i + 1) == '_') {
                sb.append('_');
                i += 2;
                continue;
            }

            // 括号层级管理
            if (c == '[') {
                bracketLevel++;
            } else if (c == ']') {
                bracketLevel--;
            }

            // 下划线替换逻辑
            if (c == '_' && bracketLevel == 0) {
                sb.append("(^|$|[,+])");
            } else {
                sb.append(c);
            }

            i++;
        }

        return sb.toString();
    }
}
