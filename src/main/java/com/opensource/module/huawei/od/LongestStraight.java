package com.opensource.module.huawei.od;

import java.util.*;

public class LongestStraight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hand = scanner.nextLine();
        String played = scanner.nextLine();

        // 牌面顺序：3~A
        String[] cards = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            indexMap.put(cards[i], i);
        }

        // 初始化每种牌4张
        int[] count = new int[12];
        Arrays.fill(count, 4);

        // 减去手上的牌
        for (String c : hand.split("-")) {
            Integer idx = indexMap.get(c);
            if (idx != null) {
                count[idx]--;
            }
        }

        // 减去已出的牌
        for (String c : played.split("-")) {
            Integer idx = indexMap.get(c);
            if (idx != null) {
                count[idx]--;
            }
        }

        // 找最长顺子（从高牌往低牌找）
        int maxLen = 0;
        int startIndex = -1;

        for (int i = 11; i >= 0; i--) {
            if (count[i] > 0) {  // 当前牌可用才开始检查
                int len = 0;
                int j = i;
                // 从当前牌开始往前（往低牌）检查连续性
                while (j >= 0 && count[j] > 0) {
                    len++;
                    j--;
                }

                // 更新最长顺子
                if (len >= 5 && len > maxLen) {
                    maxLen = len;
                    startIndex = j + 1;  // j停在连续段的前一张
                }
                // 跳过已经检查过的连续段
                i = j + 1;  // 下次从连续段之前开始检查
            }
        }

        // 输出结果
        if (maxLen < 5) {
            System.out.println("NO-CHAIN");
        } else {
            List<String> result = new ArrayList<>();
            for (int k = startIndex; k < startIndex + maxLen; k++) {
                result.add(cards[k]);
            }
            System.out.println(String.join("-", result));
        }
    }
}