package com.opensource.module.huawei.od;

import java.util.*;

/***
 *题目描述
 * 构造数列，第一个数为n，后面的数不大于前一个的一半，数列奇偶相间或许全为奇数或者全为偶数，数列的元素都是正整数，能构造多少数列。
 *
 * 输入描述
 * 输入一个n
 *
 * 备注
 * 1 <= n < 10000
 * 输出描述
 * 输出可以构造的序列个数
 *
 *
 */
public class NumSequence {

    private static Map<String, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        long total = 0;
        if (n % 2 == 1) {
            // 奇数开头：可以奇偶相间(从奇开始)，或者全奇
            total += dfs(n, 0); // 奇偶相间，当前是奇数
            total += dfs(n, 2); // 全奇
        } else {
            // 偶数开头：可以奇偶相间(从偶开始)，或者全偶
            total += dfs(n, 1); // 奇偶相间，当前是偶数
            total += dfs(n, 3); // 全偶
        }

        System.out.println(total);
    }

    /**
     * @param x 当前项的值
     * @param rule 规则类型：
     *             0 = 奇偶相间，当前是奇数
     *             1 = 奇偶相间，当前是偶数
     *             2 = 全奇数
     *             3 = 全偶数
     * @return 从当前状态能构造的数列个数
     */
    private static long dfs(int x, int rule) {
        String key = x + "," + rule;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // 当前x本身作为一个序列
        long count = 1;
        int half = x / 2;

        // 如果没有可选的下一项，直接返回1
        if (half == 0) {
            memo.put(key, count);
            return count;
        }

        // 枚举下一项y：1 <= y <= half
        for (int y = 1; y <= half; y++) {
            switch (rule) {
                case 0: // 当前奇，下一项必须偶
                    if (y % 2 == 0) {
                        count += dfs(y, 1);
                    }
                    break;
                case 1: // 当前偶，下一项必须奇
                    if (y % 2 == 1) {
                        count += dfs(y, 0);
                    }
                    break;
                case 2: // 全奇
                    if (y % 2 == 1) {
                        count += dfs(y, 2);
                    }
                    break;
                case 3: // 全偶
                    if (y % 2 == 0) {
                        count += dfs(y, 3);
                    }
                    break;
            }
        }

        memo.put(key, count);
        return count;
    }
}
