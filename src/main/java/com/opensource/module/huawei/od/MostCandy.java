package com.opensource.module.huawei.od;

import java.util.*;

public class MostCandy {

    static int N;
    static int[][] map;
    static int[][] dist;
    static int[][] candy;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new int[N][N];
        dist = new int[N][N];
        candy = new int[N][N];

        int sx=0, sy=0, tx=0, ty=0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == -3) { sx=i; sy=j; map[i][j]=0;} // 妈妈
                if (map[i][j] == -2) { tx=i; ty=j; map[i][j]=0;} // 宝宝
            }
        }

        for (int i=0;i<N;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            Arrays.fill(candy[i], -1);
        }

        // BFS
        Queue<int[]> q = new LinkedList<>();
        dist[sx][sy] = 0;
        candy[sx][sy] = 0;
        q.add(new int[]{sx, sy});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!in(nx, ny) || map[nx][ny] == -1) continue;

                int nextDist = dist[x][y] + 1;
                int nextCandy = candy[x][y] + map[nx][ny];

                if (nextDist < dist[nx][ny]) {
                    dist[nx][ny] = nextDist;
                    candy[nx][ny] = nextCandy;
                    q.add(new int[]{nx, ny});
                } else if (nextDist == dist[nx][ny] && nextCandy > candy[nx][ny]) {
                    candy[nx][ny] = nextCandy;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        if (dist[tx][ty] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(candy[tx][ty]);
        }
    }

    static boolean in(int x,int y){ return x>=0 && x<N && y>=0 && y<N; }
}