package com.opensource.module.huawei.od;

import java.util.*;

public class LookForGoldMines {
    static int n, m;
    static char[][] grid;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine().trim();
            if (s.length() == 0) continue;
            lines.add(s);
        }

        n = lines.size();
        m = lines.get(0).length();
        grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        visited = new boolean[n][m];

        int maxValue = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] != '0') {
                    maxValue = Math.max(maxValue, bfs(i, j));
                }
            }
        }

        System.out.println(maxValue);
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int total = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            total += (grid[cx][cy] == '1' ? 1 : 2);

            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                        !visited[nx][ny] && grid[nx][ny] != '0') {

                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return total;
    }
}
