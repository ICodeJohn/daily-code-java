package com.opensource.module.huawei.od;

import java.util.*;

public class MinorNum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(sc.next());
        }
        int n = list.size();
        String[] arr = list.toArray(new String[0]);

        // 基本比较器：按 (a+b) 与 (b+a) 的字典序
        Comparator<String> cmp = (a, b) -> (a + b).compareTo(b + a);
        Arrays.sort(arr, cmp);

        // 1) 尝试所有首位以非 '0' 开头的候选，并在这些候选中选最小的数值
        String bestNonZero = null;
        for (int i = 0; i < n; i++) {
            if (arr[i].charAt(0) != '0') {
                // 把 arr[i] 固定为第一位
                List<String> rest = new ArrayList<>();
                for (int j = 0; j < n; j++) if (j != i) rest.add(arr[j]);
                rest.sort(cmp); // 剩余按贪心规则排序
                StringBuilder sb = new StringBuilder();
                sb.append(arr[i]);
                for (String s : rest) sb.append(s);
                bestNonZero = sb.toString();
                break;
            }
        }

        if (bestNonZero != null) {
            // 找到以非0开头的最小数值拼接，直接输出（它不会以 '0' 开头）
            System.out.println(bestNonZero);
            return;
        }

        // 2) 否则：说明所有字符串都以 '0' 开头，按原比较器排序并拼接，然后去掉前导零
        Arrays.sort(arr, cmp);
        StringBuilder sb = new StringBuilder();
        for (String s : arr) sb.append(s);
        String res = sb.toString().replaceFirst("^0+", "");
        if (res.isEmpty()) res = "0";
        System.out.println(res);
    }
}
