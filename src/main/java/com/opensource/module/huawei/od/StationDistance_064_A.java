package com.opensource.module.huawei.od;

import java.util.Scanner;

public class StationDistance_064_A {

    private static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取站点数量
        int n = scanner.nextInt();
        int[][] distances = new int[n][n];

        // 读取距离矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = scanner.nextInt();
            }
        }

        // 计算最短路径
        int result = findShortestPath(n, distances);
        System.out.println(result);

        scanner.close();
    }

    public static int findShortestPath(int n, int[][] distances) {
        minDistance = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n];

        // 从基站1（索引0）开始
        visited[0] = true;
        dfs(0, 1, 0, n, distances, visited);

        return minDistance;
    }

    /**
     * 深度优先搜索
     * @param current 当前基站索引
     * @param count 已访问基站数量
     * @param currentDist 当前累计距离
     * @param n 基站总数
     * @param distances 距离矩阵
     * @param visited 访问标记数组
     */
    private static void dfs(int current, int count, int currentDist,
                            int n, int[][] distances, boolean[] visited) {

        // 如果已经访问完所有基站，返回基站1并更新最小值
        if (count == n) {
            int totalDistance = currentDist + distances[current][0];
            if (totalDistance < minDistance) {
                minDistance = totalDistance;
            }
            return;
        }

        // 尝试访问每一个未访问的基站
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // 剪枝：如果当前距离加上下一步已经超过最小值，则跳过
                if (currentDist + distances[current][i] >= minDistance) {
                    continue;
                }

                visited[i] = true;
                dfs(i, count + 1, currentDist + distances[current][i], n, distances, visited);
                visited[i] = false; // 回溯
            }
        }
    }
}