package com.opensource.module.huawei.od;

import java.util.*;

public class LargestO {
    static int m, n;
    static char[][] grid;
    static boolean[][] visited;
    // 方向数组：上、下、左、右
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static class Region {
        int size;
        List<Point> entries = new ArrayList<>();
        Point entryPoint; // 唯一入口坐标
        Region(int size, List<Point> entries) {
            this.size = size;
            this.entries = entries;
            if (entries.size() == 1) {
                this.entryPoint = entries.get(0);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        sc.nextLine(); // 吸掉换行符

        grid = new char[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                grid[i][j] = line[j].charAt(0);
            }
        }

        List<Region> singleEntryRegions = new ArrayList<>();

        // 遍历矩阵，找到所有连通区域
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 'O') {
                    Region region = bfs(i, j);
                    if (region.entries.size() == 1) {
                        singleEntryRegions.add(region);
                    }
                }
            }
        }

        if (singleEntryRegions.isEmpty()) {
            System.out.println("NULL");
            return;
        }

        // 找最大区域
        int maxSize = 0;
        for (Region r : singleEntryRegions) {
            if (r.size > maxSize) maxSize = r.size;
        }

        // 找所有最大单入口区域
        List<Region> maxRegions = new ArrayList<>();
        for (Region r : singleEntryRegions) {
            if (r.size == maxSize) maxRegions.add(r);
        }

        if (maxRegions.size() == 1) {
            Region r = maxRegions.get(0);
            System.out.println(r.entryPoint.x + " " + r.entryPoint.y + " " + r.size);
        } else {
            System.out.println(maxSize);
        }
    }

    // BFS 遍历连通区域，返回区域信息
    static Region bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> entries = new ArrayList<>();
        int size = 0;

        visited[x][y] = true;
        queue.offer(new Point(x, y));
        size++;

        // 判断是否是边界
        if (isBoundary(x, y)) entries.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] == 'O') {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                    size++;
                    if (isBoundary(nx, ny)) {
                        entries.add(new Point(nx, ny));
                    }
                }
            }
        }

        return new Region(size, entries);
    }

    // 判断是否是边界
    static boolean isBoundary(int x, int y) {
        return x == 0 || x == m - 1 || y == 0 || y == n - 1;
    }
}
