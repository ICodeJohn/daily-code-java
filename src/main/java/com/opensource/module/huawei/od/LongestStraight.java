package com.opensource.module.huawei.od;

import java.util.*;

public class LongestStraight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hand = scanner.nextLine();
        String played = scanner.nextLine();

        // 牌面顺序
        String[] cards = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            indexMap.put(cards[i], i);
        }

        int[] count = new int[12]; // 3~A 共12种
        Arrays.fill(count, 4);

        // 减去手上的牌
        for (String c : hand.split("-")) {
            if (indexMap.containsKey(c)) {
                count[indexMap.get(c)]--;
            }
        }

        // 减去已出的牌
        for (String c : played.split("-")) {
            if (indexMap.containsKey(c)) {
                count[indexMap.get(c)]--;
            }
        }

        // 找最长顺子（从高牌往低牌找，保证牌面最大）
        int maxLen = 0;
        int startIndex = -1;

        for (int i = 11; i >= 0; i--) {
            int len = 0;
            int j = i;
            while (j >= 0 && count[j] > 0) {
                len++;
                j--;
            }
            if (len >= 5 && len > maxLen) {
                maxLen = len;
                startIndex = j + 1; // 连续段的开始
                // 因为从高往低找，第一个最长的就是牌面最大的
            }
        }

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
