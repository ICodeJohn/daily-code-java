package com.opensource.module.huawei.od;

import java.util.*;

public class MergePortGroups {
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
            if (line.isEmpty()) {
                // 空行也算一个端口组，但无端口
                setList.add(new HashSet<>());
                continue;
            }
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
        boolean merged;
        do {
            merged = false;
            for (int i = 0; i < setList.size(); i++) {
                for (int j = i + 1; j < setList.size(); ) {
                    Set<Integer> set1 = setList.get(i);
                    Set<Integer> set2 = setList.get(j);
                    // 计算公共不同端口数
                    int common = 0;
                    for (Integer x : set1) {
                        if (set2.contains(x)) {
                            common++;
                            if (common >= 2) break;
                        }
                    }
                    if (common >= 2) {
                        // 合并
                        set1.addAll(set2);
                        setList.remove(j);
                        merged = true;
                    } else {
                        j++;
                    }
                }
            }
        } while (merged);

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
}
