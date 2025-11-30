package com.opensource.module.huawei.od;

import java.util.Scanner;

public class TerrainElevation {

    static int m, n;
    static int[][] grid;
    static int maxSteps = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 从每个格子开始搜索
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n];
                visited[i][j] = true;
                dfs(i, j, visited, 0, 0); // 0表示第一步，没有上一步方向
            }
        }

        System.out.println(maxSteps);
    }

    // lastDir: 0=第一步, 1=上一步是上坡, -1=上一步是下坡
    static void dfs(int x, int y, boolean[][] visited, int steps, int lastDir) {
        maxSteps = Math.max(maxSteps, steps);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
                continue;
            }

            if (grid[nx][ny] == grid[x][y]) {
                continue; // 高度相同不能走
            }

            int currentDir = (grid[nx][ny] > grid[x][y]) ? 1 : -1;

            // 检查交替规则
            if (lastDir == 0 || lastDir != currentDir) {
                visited[nx][ny] = true;
                dfs(nx, ny, visited, steps + 1, currentDir);
                visited[nx][ny] = false;
            }
        }
    }
}
