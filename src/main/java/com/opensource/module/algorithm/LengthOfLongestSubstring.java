package com.opensource.module.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: 无重复字符的最长子串
 * @Description: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是子串的长度，"pwke"是一个子序列，不是子串。
 * <p>
 * 示例4:
 * 输入: s = "abba"
 * 输出: 2
 * 解释: 因为无重复字符的最长子串是 "ab"，所以其长度为 2。
 * @Author: ZhaoWei
 * @Date: 2023/7/18 08:59
 * @Version V1.0
 */
public class LengthOfLongestSubstring {

    public int solution(String str) {
        int left = 0;
        int max = 0;
        int len = str.length();
        Map<Character, Integer> index = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Character ch = str.charAt(i);
            if (index.containsKey(ch)) {
                left = Math.max(left, index.get(ch));
            }
            index.put(ch, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
