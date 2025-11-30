package com.opensource.module.huawei.od;

import java.util.*;

public class RelativeOpenSyllable {

    // 判断是否为辅音
    private static boolean isConsonant(char ch) {
        return Character.isLetter(ch) && "aeiou".indexOf(ch) == -1;
    }

    // 判断是否为元音
    private static boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) != -1;
    }

    // 检查单词是否全是字母
    private static boolean isAllLetters(String word) {
        for (char c : word.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        String[] words = input.split(" ");
        List<String> newWords = new ArrayList<>();

        // 第一步：反转纯字母单词
        for (String word : words) {
            if (isAllLetters(word)) {
                newWords.add(new StringBuilder(word).reverse().toString());
            } else {
                newWords.add(word);
            }
        }

        // 拼接新字符串
        String newStr = String.join(" ", newWords);

        // 第二步：统计相对开音节子串
        int count = 0;
        for (int i = 0; i <= newStr.length() - 4; i++) {
            String sub = newStr.substring(i, i + 4);

            // 检查四个字符是否都是字母
            boolean allLetters = true;
            for (char c : sub.toCharArray()) {
                if (!Character.isLetter(c)) {
                    allLetters = false;
                    break;
                }
            }

            if (!allLetters) {
                continue;
            }

            char c1 = sub.charAt(0);
            char v = sub.charAt(1);
            char c2 = sub.charAt(2);
            char e = sub.charAt(3);

            if (isConsonant(c1) &&
                    isVowel(v) &&
                    isConsonant(c2) && c2 != 'r' &&
                    e == 'e') {
                count++;
            }
        }

        System.out.println(count);
        scanner.close();
    }
}
