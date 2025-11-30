package com.opensource.module.huawei.od;

import java.util.*;

public class CodeBook {
    static int N, M;
    static int[] plaintext;
    static int[][] book;
    static List<int[]> bestPath = null;
    static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右、下、左、上 顺序

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        plaintext = new int[N];
        for (int i = 0; i < N; i++) {
            plaintext[i] = sc.nextInt();
        }

        M = sc.nextInt();
        book = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                book[i][j] = sc.nextInt();
            }
        }

        // 搜索
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (book[i][j] == plaintext[0]) {
                    boolean[][] visited = new boolean[M][M];
                    List<int[]> path = new ArrayList<>();
                    path.add(new int[]{i, j});
                    visited[i][j] = true;
                    if (dfs(1, i, j, visited, path)) {
                        outputResult(bestPath);
                        return;
                    }
                }
            }
        }

        System.out.println("error");
    }

    static boolean dfs(int index, int x, int y, boolean[][] visited, List<int[]> path) {
        if (index == N) {
            bestPath = new ArrayList<>(path);
            return true;
        }

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < M && ny >= 0 && ny < M && !visited[nx][ny] && book[nx][ny] == plaintext[index]) {
                visited[nx][ny] = true;
                path.add(new int[]{nx, ny});
                if (dfs(index + 1, nx, ny, visited, path)) {
                    return true;
                }
                path.remove(path.size() - 1);
                visited[nx][ny] = false;
            }
        }
        return false;
    }

    static void outputResult(List<int[]> path) {
        StringBuilder sb = new StringBuilder();
        for (int[] p : path) {
            sb.append(p[0]).append(" ").append(p[1]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
