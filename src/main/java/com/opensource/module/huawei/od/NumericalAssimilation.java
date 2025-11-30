package com.opensource.module.huawei.od;

import java.util.*;

public class NumericalAssimilation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] grid = new int[m][n];

        // 读取矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 将起点设为1
        grid[0][0] = 1;

        // 使用BFS模拟传播过程
        int result = bfs(grid, m, n);
        System.out.println(result);

        scanner.close();
    }

    private static int bfs(int[][] grid, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        // 起点入队
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            // 向四个方向传播
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 检查边界和访问状态
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    // 只能感染0
                    if (grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;  // 感染
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                    // 如果是2，标记为已访问但不入队（障碍物）
                    else if (grid[nx][ny] == 2) {
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        // 统计非1的元素数量
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
