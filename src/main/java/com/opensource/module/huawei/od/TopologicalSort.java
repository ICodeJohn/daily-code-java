package com.opensource.module.huawei.od;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] deps = line.split(" ");

        // 建图：task -> list of tasks that depend on it
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // 初始化所有出现的任务
        Set<String> allTasks = new HashSet<>();

        for (String dep : deps) {
            String[] parts = dep.split("->");
            String from = parts[1];  // 被依赖的任务
            String to = parts[0];    // 依赖别人的任务
            allTasks.add(from);
            allTasks.add(to);

            // 建立依赖关系：from -> to 表示 from 完成后 to 才能开始
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            // 增加 to 的入度
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            // 确保 from 也在 inDegree 中（如果入度是0）
            inDegree.putIfAbsent(from, 0);
        }

        // 优先队列（最小堆）按字母顺序
        PriorityQueue<String> queue = new PriorityQueue<>();
        for (String task : allTasks) {
            if (inDegree.getOrDefault(task, 0) == 0) {
                queue.offer(task);
            }
        }

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            result.add(cur);

            // 遍历所有依赖 cur 的任务
            if (graph.containsKey(cur)) {
                for (String neighbor : graph.get(cur)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // 输出结果
        System.out.println(String.join(" ", result));
    }
}