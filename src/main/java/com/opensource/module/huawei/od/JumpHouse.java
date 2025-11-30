package com.opensource.module.huawei.od;

import java.util.*;

public class JumpHouse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取数组
        String arrayStr = scanner.nextLine();
        int[] steps = parseArray(arrayStr);

        // 读取目标值
        int count = scanner.nextInt();

        // 寻找最优解
        int[] result = findOptimalPair(steps, count);

        // 输出结果
        System.out.println("[" + result[0] + ", " + result[1] + "]");

        scanner.close();
    }

    // 解析数组输入
    private static int[] parseArray(String str) {
        String[] parts = str.substring(1, str.length() - 1).split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }
        return arr;
    }

    // 寻找最优的两数组合
    private static int[] findOptimalPair(int[] steps, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // 存储值到索引的映射
        int minIndexSum = Integer.MAX_VALUE;
        int[] result = new int[2];

        for (int i = 0; i < steps.length; i++) {
            int complement = target - steps[i];

            // 检查补数是否存在于哈希表中
            if (map.containsKey(complement)) {
                int j = map.get(complement);
                int currentIndexSum = i + j;

                // 如果找到更小的索引和，更新结果
                if (currentIndexSum < minIndexSum) {
                    minIndexSum = currentIndexSum;
                    // 保持原有顺序输出
                    if (j < i) {
                        result[0] = steps[j];
                        result[1] = steps[i];
                    } else {
                        result[0] = steps[i];
                        result[1] = steps[j];
                    }
                }
            }

            // 如果当前值不在map中，或者需要更新为更小的索引
            if (!map.containsKey(steps[i])) {
                map.put(steps[i], i);
            }
        }

        return result;
    }
}
