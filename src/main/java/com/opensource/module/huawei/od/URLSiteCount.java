package com.opensource.module.huawei.od;

import java.util.*;

public class URLSiteCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> urlCountMap = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.matches("\\d+")) { // 如果是数字
                int n = Integer.parseInt(line);

                // 自定义排序：先按访问次数降序，再按URL字典序升序
                List<Map.Entry<String, Integer>> sortedList = urlCountMap.entrySet()
                        .stream()
                        .sorted((a, b) -> {
                            int countCompare = b.getValue().compareTo(a.getValue());
                            if (countCompare != 0) return countCompare;
                            return a.getKey().compareTo(b.getKey());
                        })
                        .toList();

                // 输出前N个
                List<String> topN = new ArrayList<>();
                for (int i = 0; i < Math.min(n, sortedList.size()); i++) {
                    topN.add(sortedList.get(i).getKey());
                }
                System.out.println(String.join(",", topN));

            } else { // 如果是URL
                urlCountMap.put(line, urlCountMap.getOrDefault(line, 0) + 1);
            }
        }
        scanner.close();
    }
}
