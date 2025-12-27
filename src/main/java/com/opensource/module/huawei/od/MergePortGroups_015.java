package com.opensource.module.huawei.od;

import java.util.*;

public class  MergePortGroups_015 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读 M
        int M = scanner.nextInt();
        scanner.nextLine(); // 换行

        // 范围检查
        if (M < 1 || M > 10) {
            System.out.println("[[]]");
            return;
        }

        List<Set<Integer>> setList = new ArrayList<>();

        // 读每组数据
        for (int i = 0; i < M; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(",");
            // 检查 N
            if (parts.length < 1 || parts.length > 100) {
                System.out.println("[[]]");
                return;
            }
            Set<Integer> set = new HashSet<>();
            for (String p : parts) {
                int num = Integer.parseInt(p);
                set.add(num);
            }
            setList.add(set);
        }

        // 合并过程
        do {
            int[] mergeIdx = getMergeIdx(setList);
            if (mergeIdx == null) {
                break;
            }
            setList.get(mergeIdx[0]).addAll(setList.get(mergeIdx[1]));
            setList.remove(mergeIdx[1]);
        } while (true);

        // 准备输出：每个组去重排序
        List<List<Integer>> result = new ArrayList<>();
        for (Set<Integer> set : setList) {
            List<Integer> sorted = new ArrayList<>(set);
            Collections.sort(sorted);
            result.add(sorted);
        }

        // 输出
        System.out.println(result);
    }

    static int[] getMergeIdx(List<Set<Integer>> nums) {
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int same = 0;
                for (Integer num1 : nums.get(i)) {
                    for (Integer num2 : nums.get(j)) {
                        if (num1.equals(num2)) {
                            same++;
                        }
                        if (same >= 2) {
                            return new int[]{i, j};
                        }
                    }
                }
            }
        }
        return null;
    }
}
