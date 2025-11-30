package com.opensource.module.huawei.od;

import java.util.*;

public class CpuAffinityScheduler {

    // 主函数：array = 可用处理器列表，num = 申请数量
    public static List<List<Integer>> schedule(int[] array, int num) {
        // 链路分组
        int[] linkA = {0, 1, 2, 3};
        int[] linkB = {4, 5, 6, 7};

        Set<Integer> free = new HashSet<>();
        for (int cpu : array) free.add(cpu);

        // 计算每个链路的可用 CPU
        List<Integer> freeA = new ArrayList<>();
        List<Integer> freeB = new ArrayList<>();

        for (int c : linkA) if (free.contains(c)) freeA.add(c);
        for (int c : linkB) if (free.contains(c)) freeB.add(c);

        // 特殊情况：num = 8，需要全部使用
        if (num == 8) {
            if (array.length == 8) {
                List<List<Integer>> res = new ArrayList<>();
                List<Integer> all = new ArrayList<>();
                for (int i : array) all.add(i);
                res.add(all);
                return res;
            } else {
                return new ArrayList<>();
            }
        }

        // 根据 num 获取优先级顺序（剩余可用CPU数量）
        List<Integer> priority = getPriority(num);

        // 尝试链路 A 和 B
        Integer bestRemain = null;  // 选中的剩余CPU数
        List<Integer> bestLink = null; // 选中的链路可用 CPU

        for (int targetRemain : priority) {
            // 链路A检查
            if (freeA.size() == targetRemain && freeA.size() >= num) {
                bestRemain = targetRemain;
                bestLink = freeA;
                break;
            }
            // 链路B检查
            if (freeB.size() == targetRemain && freeB.size() >= num) {
                bestRemain = targetRemain;
                bestLink = freeB;
                break;
            }
        }

        // 没找到符合的链路
        if (bestLink == null) return new ArrayList<>();

        // 生成所有大小为 num 的组合
        return combinations(bestLink, num);
    }

    // 获取优先级规则
    private static List<Integer> getPriority(int num) {
        if (num == 1) return Arrays.asList(1, 3, 2, 4);
        if (num == 2) return Arrays.asList(2, 4, 3);
        if (num == 4) return Arrays.asList(4);
        return new ArrayList<>();
    }

    // 从一个 list 中取 num 个元素的所有组合
    private static List<List<Integer>> combinations(List<Integer> list, int num) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(list, num, 0, new ArrayList<>(), ans);
        return ans;
    }

    private static void backtrack(List<Integer> list, int num, int start,
                                  List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == num) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            path.add(list.get(i));
            backtrack(list, num, i + 1, path, ans);
            path.remove(path.size() - 1);
        }
    }


    // ==== 测试 ====
    public static void main(String[] args) {
        int[] array1 = {0, 1, 4, 5, 6, 7};
        System.out.println(schedule(array1, 1)); // [[0], [1]]

        int[] array2 = {0, 1, 4, 5, 6, 7};
        System.out.println(schedule(array2, 4)); // [[4, 5, 6, 7]]
    }
}
