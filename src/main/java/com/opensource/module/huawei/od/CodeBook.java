package com.opensource.module.huawei.od;

import java.util.*;

public class CodeBook {
    static int N, M;
    static int[] data;
    static int[][] book;
    static boolean[][] visited;

    // 按坐标字典序：先行小，再列小
    // 上、左、右、下
    static final int[][] DIRS = {
            {-1, 0},
            {0, -1},
            {0, 1},
            {1, 0}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 明文长度
        N = sc.nextInt();
        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
        }

        // 密码本尺寸
        M = sc.nextInt();
        book = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                book[i][j] = sc.nextInt();
            }
        }

        // ===== 频次剪枝 =====
        int[] need = new int[10];
        int[] have = new int[10];
        for (int d : data) need[d]++;
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                have[book[i][j]]++;

        for (int d = 0; d <= 9; d++) {
            if (need[d] > have[d]) {
                System.out.println("error");
                return;
            }
        }

        // ===== 枚举起点（按字典序） =====
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (book[i][j] == data[0]) {
                    visited = new boolean[M][M];
                    List<int[]> path = new ArrayList<>();

                    visited[i][j] = true;
                    path.add(new int[]{i, j});

                    if (dfs(1, i, j, path)) {
                        print(path);
                        return;
                    }
                }
            }
        }

        System.out.println("error");
    }

    // DFS 匹配 data[index]
    static boolean dfs(int index, int x, int y, List<int[]> path) {
        if (index == N) {
            return true;
        }

        for (int[] d : DIRS) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || nx >= M || ny < 0 || ny >= M) continue;
            if (visited[nx][ny]) continue;
            if (book[nx][ny] != data[index]) continue;

            visited[nx][ny] = true;
            path.add(new int[]{nx, ny});

            if (dfs(index + 1, nx, ny, path)) {
                return true; // 第一个成功的一定是字典序最小
            }

            path.remove(path.size() - 1);
            visited[nx][ny] = false;
        }

        return false;
    }

    static void print(List<int[]> path) {
        StringBuilder sb = new StringBuilder();
        for (int[] p : path) {
            sb.append(p[0]).append(" ").append(p[1]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
