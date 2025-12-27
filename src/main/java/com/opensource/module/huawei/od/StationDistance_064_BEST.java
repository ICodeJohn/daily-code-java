package com.opensource.module.huawei.od;

import java.util.Scanner;

public class StationDistance_064_BEST {

    static int ans = Integer.MAX_VALUE;

    private static void dfs(
            int n,
            int[][] dist,
            boolean[] visited,
            int count,
            int last,
            int cost
    ) {
        // 剪枝
        if (cost >= ans) return;

        // 已访问完所有非 0 点
        if (count == n - 1) {
            ans = Math.min(ans, cost + dist[last][0]);
            return;
        }

        for (int i = 1; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(n, dist, visited, count + 1, i, cost + dist[last][i]);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = sc.nextInt();
            }
        }

        boolean[] visited = new boolean[n];
        visited[0] = true; // 起点

        dfs(n, dist, visited, 0, 0, 0);

        System.out.println(ans);
    }
}
