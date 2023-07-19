package com.opensource.module.algorithm;

/**
 * @Title: 字符串相加
 * @Description: 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。你不能使用任何內建的用于处理
 * 大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 示例1:
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * <p>
 * 示例2:
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * <p>
 * 示例3:
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 * <p>
 * 最终点评:
 * 题目包含心机，首先考察了char的减法运算，同时Stringbuilder是逆向相加最后需要反转，
 * 最重要的是进位需要考虑进位超出相加字符串长度的情况。
 * @Author: ZhaoWei
 * @Date: 2023/7/17 14:10
 * @Version V1.0
 */
public class AddStrings {

    public String solution(String num1, String num2) {
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int sum = 0;
        StringBuilder builder = new StringBuilder();

        while (len1 >= 0 || len2 >= 0 || sum > 0) {
            int a = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int b = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
            sum += a + b;
            builder.append(sum % 10);
            sum = sum / 10;
            len1--;
            len2--;
        }

        builder.reverse();
        return builder.toString();
    }

    public static void main(String[] args) {
        AddStrings sum = new AddStrings();
        String res = sum.solution("9", "1");
        System.out.println(res);
    }
}
