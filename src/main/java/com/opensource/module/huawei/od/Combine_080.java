package com.opensource.module.huawei.od;

import java.util.*;

public class Combine_080 {

    /**
     * 去除字符串前导零，避免输出多余的 0
     */
    private static String formatPrint(String result) {
        return result.replaceFirst("^0+", "").isEmpty() ? "0" : result.replaceFirst("^0+", "");
    }

    /**
     * 计算最小拼接数
     */
    private static String minCombination(List<String> zeroArr, List<String> noZeroArr) {
        // 按照拼接规则排序
        zeroArr.sort((a, b) -> (a + b).compareTo(b + a));
        noZeroArr.sort((a, b) -> (a + b).compareTo(b + a));

        // 拼接所有零前缀的部分
        String zeroResult = String.join("", zeroArr);

        // 如果没有非零数，返回处理后的零字符串
        if (noZeroArr.isEmpty()) {
            return formatPrint(zeroResult);
        }

        // 选择最优的非零头部
        int bestIndex = 0;
        for (int i = 1; i < noZeroArr.size(); i++) {
            if (noZeroArr.get(i).charAt(0) != noZeroArr.get(0).charAt(0)) {
                break;
            }
            if ((noZeroArr.get(i) + zeroResult + noZeroArr.get(0))
                    .compareTo(noZeroArr.get(0) + zeroResult + noZeroArr.get(i)) < 0) {
                bestIndex = i;
            }
        }

        // 组合最终结果
        StringBuilder result = new StringBuilder();
        result.append(noZeroArr.get(bestIndex)).append(zeroResult);
        for (int i = 0; i < noZeroArr.size(); i++) {
            if (i != bestIndex) result.append(noZeroArr.get(i));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> zeroArr = new ArrayList<>(), noZeroArr = new ArrayList<>();

        // 读取输入
        while (scanner.hasNext()) {
            String s = scanner.next();
            if (s.charAt(0) == '0') {
                zeroArr.add(s);
            } else {
                noZeroArr.add(s);
            }
        }
        scanner.close();
        System.out.println(minCombination(zeroArr, noZeroArr));
    }
}
