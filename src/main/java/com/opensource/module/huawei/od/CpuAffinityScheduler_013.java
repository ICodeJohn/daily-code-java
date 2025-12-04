package com.opensource.module.huawei.od;

import java.util.*;

public class CpuAffinityScheduler_013 {

    // 主函数：array = 可用处理器列表，num = 申请数量
    public static List<List<Integer>> schedule(int[] array, int num) {
        int[] linkA = {0, 1, 2, 3};
        int[] linkB = {4, 5, 6, 7};

        Set<Integer> free = new HashSet<>();
        for (int cpu : array) free.add(cpu);

        List<Integer> freeA = new ArrayList<>();
        List<Integer> freeB = new ArrayList<>();
        for (int c : linkA) if (free.contains(c)) freeA.add(c);
        for (int c : linkB) if (free.contains(c)) freeB.add(c);

        // num = 8 必须两条链路全满
        if (num == 8) {
            if (freeA.size() == 4 && freeB.size() == 4) {
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < 8; i++) all.add(i);
                return Collections.singletonList(all);
            }
            return new ArrayList<>();
        }

        // 获取优先级
        List<Integer> priority = getPriority(num);

        // 这次：我们不再优先选择链路 A
        // 而是同时收集所有满足当前优先级的链路
        List<List<Integer>> candidateLinks = new ArrayList<>();

        for (int targetCount : priority) {
            boolean found = false;

            if (freeA.size() == targetCount && freeA.size() >= num) {
                candidateLinks.add(freeA);
                found = true;
            }
            if (freeB.size() == targetCount && freeB.size() >= num) {
                candidateLinks.add(freeB);
                found = true;
            }

            if (found) break;  // 不再检查后续优先级
        }

        if (candidateLinks.isEmpty()) return new ArrayList<>();

        // num = 1：所有 candidateLinks 都要输出所有 CPU
        if (num == 1) {
            List<List<Integer>> result = new ArrayList<>();
            for (List<Integer> link : candidateLinks) {
                for (Integer cpu : link) {
                    result.add(Arrays.asList(cpu));
                }
            }
            return result;
        }

        // num = 2 或 4：对每条满足优先级的链路生成组合
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> link : candidateLinks) {
            result.addAll(combinations(link, num));
        }

        return result;
    }

    // 根据 num 生成优先级
    private static List<Integer> getPriority(int num) {
        if (num == 1) return Arrays.asList(1, 3, 2, 4);
        if (num == 2) return Arrays.asList(2, 4, 3);
        if (num == 4) return Arrays.asList(4);
        return new ArrayList<>();
    }

    // 组合生成
    private static List<List<Integer>> combinations(List<Integer> list, int num) {
        List<List<Integer>> ans = new ArrayList<>();
        Collections.sort(list);
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

    // 测试代码
    public static void main(String[] args) {
        int[] array1 = {0, 1, 4, 5, 6, 7};
        System.out.println("测试1 (num=1): " + schedule(array1, 1));
        // [[0], [1]]

        int[] array2 = {0, 1, 4, 5, 6, 7};
        System.out.println("测试2 (num=4): " + schedule(array2, 4));
        // [[4,5,6,7]]

        int[] array3 = {0, 1, 2};
        System.out.println("测试3 (num=1): " + schedule(array3, 1));
        // [[0],[1],[2]]

        int[] array4 = {0, 1, 2, 3};
        System.out.println("测试4 (num=2): " + schedule(array4, 2));
        // [[0,1],[0,2],[0,3],[1,2],[1,3],[2,3]]

        int[] array5 = {0, 1, 2, 4, 5, 6};
        System.out.println("测试5 (num=1): " + schedule(array5, 1));
        // [[0],[1],[2],[4],[5],[6]]

        int[] array6 = {0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println("测试6 (num=8): " + schedule(array6, 8));
        // [[0,1,2,3,4,5,6,7]]

        int[] array7 = {0, 1, 2, 3, 4, 5};
        System.out.println("测试7 (num=2): " + schedule(array7, 2));
        // 链路A=4个，链路B=2个 → 优先选择“剩余2个”
        // [[4,5]]
    }
}
