package com.opensource.module.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/24 11:06
 * @Version V1.0
 */
public class LetterCombinations {

    public static List<String> solution(String nums) {
        List<String> result = new ArrayList<>();
        if (nums.length() == 0) {
            return result;
        }
        Map<Character, String> map = new HashMap() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrace(result, nums, 0, map, new StringBuffer());
        return result;
    }

    private static void backtrace(List<String> result, String nums, int index, Map<Character, String> map, StringBuffer buffer) {
        if (index == nums.length()) {
            result.add(buffer.toString());
        } else {
            Character ch = nums.charAt(index);
            String letter = map.get(ch);
            for (int i = 0; i < letter.length(); ++i) {
                buffer.append(letter.charAt(i));
                backtrace(result, nums, index + 1, map, buffer);
                buffer.deleteCharAt(index);
            }
        }
    }
}
