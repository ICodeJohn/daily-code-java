package com.opensource.module.algorithm;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/23 23:07
 * @Version V1.0
 */
public class LongestPalindrome {

    public String solution(String s) {

        int len = s.length();
        if (len < 2) {
            return s;
        }
        int begin = 0;
        int maxLen = 1;

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; j++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
