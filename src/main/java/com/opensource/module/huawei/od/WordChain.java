package com.opensource.module.huawei.od;

import java.util.*;

public class WordChain {

    // 全局变量记录最终最长结果
    private static String maxChain = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int startIndex = Integer.parseInt(sc.nextLine().trim());
        int n = Integer.parseInt(sc.nextLine().trim());

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine().trim();
        }

        // 已使用标记数组
        boolean[] used = new boolean[n];
        used[startIndex] = true;

        // 按首字母构建 Map
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(words[i].charAt(0), k -> new ArrayList<>()).add(i);
        }

        // 对每个首字母的列表进行排序：长度降序 -> 字典序升序
        for (List<Integer> list : map.values()) {
            list.sort((i1, i2) -> {
                if (words[i2].length() != words[i1].length()) {
                    return words[i2].length() - words[i1].length(); // 长度降序
                } else {
                    return words[i1].compareTo(words[i2]); // 字典序升序
                }
            });
        }

        // 从起始单词开始 DFS
        dfs(words, map, used, words[startIndex], words[startIndex].charAt(words[startIndex].length() - 1));

        // 输出结果
        System.out.println(maxChain);
    }

    private static void dfs(String[] words, Map<Character, List<Integer>> map,
                            boolean[] used, String currentStr, char lastChar) {
        boolean hasNext = false;
        List<Integer> candidates = map.getOrDefault(lastChar, new ArrayList<>());

        for (int idx : candidates) {
            if (!used[idx]) {
                used[idx] = true;
                hasNext = true;
                dfs(words, map, used, currentStr + words[idx], words[idx].charAt(words[idx].length() - 1));
                used[idx] = false; // 回溯
            }
        }

        if (!hasNext) {
            // 没有更多单词可以接，更新全局最长串
            if (currentStr.length() > maxChain.length() ||
                    (currentStr.length() == maxChain.length() && currentStr.compareTo(maxChain) < 0)) {
                maxChain = currentStr;
            }
        }
    }
}
