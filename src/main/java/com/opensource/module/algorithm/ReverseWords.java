package com.opensource.module.algorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/28 08:48
 * @Version V1.0
 */
public class ReverseWords {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            String res = solution(line);
            System.out.println(res);
        }
    }

    private static String solution(String line) {
        char[] chars = line.trim().toCharArray();
        Stack<Character> stack = new Stack<>();
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (cur == ' ' || cur == '?' || cur == ',' || cur == '.') {
                for (int j = start; j < i; j++) {
                    chars[j] = stack.pop();
                }
                start = i + 1;
                if (cur != ' ') {
                    start++;
                }
            } else {
                stack.push(cur);
            }
        }
        for (int j = start; j < chars.length; j++) {
            chars[j] = stack.pop();
        }
        return new String(chars);
    }
}