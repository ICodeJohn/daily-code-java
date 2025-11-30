package com.opensource.module.huawei.od;

import java.util.*;

public class WanderingEarth {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int E = sc.nextInt();

        // 存储手动启动事件
        List<int[]> manualStarts = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int T = sc.nextInt();
            int P = sc.nextInt();
            manualStarts.add(new int[]{T, P});
        }

        // 按时间排序
        manualStarts.sort((a, b) -> Integer.compare(a[0], b[0]));

        int[] startTime = new int[N];
        Arrays.fill(startTime, -1);

        Queue<int[]> queue = new LinkedList<>(); // [位置, 启动时刻]

        // 处理手动启动
        int idx = 0;
        int currentTime = 0;
        while (idx < E || !queue.isEmpty()) {
            // 将当前时刻所有手动启动入队
            while (idx < E && manualStarts.get(idx)[0] == currentTime) {
                int pos = manualStarts.get(idx)[1];
                if (startTime[pos] == -1) {
                    startTime[pos] = currentTime;
                    queue.offer(new int[]{pos, currentTime});
                }
                idx++;
            }

            // BFS 处理
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int pos = curr[0];
                int time = curr[1];

                // 传播到相邻
                int[] neighbors = {(pos - 1 + N) % N, (pos + 1) % N};
                for (int neighbor : neighbors) {
                    if (startTime[neighbor] == -1) {
                        startTime[neighbor] = time + 1;
                        queue.offer(new int[]{neighbor, time + 1});
                    }
                }
            }
            currentTime++;
        }

        // 找到最晚启动时刻
        int maxTime = -1;
        for (int t : startTime) {
            if (t > maxTime) maxTime = t;
        }

        // 收集最晚启动的发动机
        List<Integer> lastStarts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (startTime[i] == maxTime) {
                lastStarts.add(i);
            }
        }
        Collections.sort(lastStarts);

        // 输出
        System.out.println(lastStarts.size());
        for (int i = 0; i < lastStarts.size(); i++) {
            System.out.print(lastStarts.get(i));
            if (i < lastStarts.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }
}
