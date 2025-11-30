package com.opensource.module.huawei.od;

import java.util.*;

public class RobotTag {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读房间大小
        int X = sc.nextInt();
        int Y = sc.nextInt();

        // 读墙壁数量
        int N = sc.nextInt();

        boolean[][] wall = new boolean[X][Y];
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            wall[x][y] = true;
        }

        boolean[][] reachableFromStart = new boolean[X][Y];
        boolean[][] canReachEnd = new boolean[X][Y];

        // 正向 DP: 从 (0,0) 出发，只能向右(x+1)或向上(y+1)
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (wall[x][y]) continue;
                if (x == 0 && y == 0) {
                    reachableFromStart[x][y] = true;
                } else {
                    boolean fromLeft = (x > 0) ? reachableFromStart[x-1][y] : false;
                    boolean fromDown = (y > 0) ? reachableFromStart[x][y-1] : false;
                    reachableFromStart[x][y] = fromLeft || fromDown;
                }
            }
        }

        // 反向 DP: 从 (X-1,Y-1) 出发，只能向左(x-1)或向下(y-1)
        for (int x = X-1; x >= 0; x--) {
            for (int y = Y-1; y >= 0; y--) {
                if (wall[x][y]) continue;
                if (x == X-1 && y == Y-1) {
                    canReachEnd[x][y] = true;
                } else {
                    boolean toRight = (x < X-1) ? canReachEnd[x+1][y] : false;
                    boolean toUp = (y < Y-1) ? canReachEnd[x][y+1] : false;
                    canReachEnd[x][y] = toRight || toUp;
                }
            }
        }

        int trapCount = 0;      // 陷阱 B
        int unreachableCount = 0; // 不可达 A

        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (wall[x][y]) continue; // 跳过墙壁
                if (!reachableFromStart[x][y]) {
                    unreachableCount++; // 正向不可达
                } else if (!canReachEnd[x][y]) {
                    trapCount++;        // 可到达但到不了出口
                }
            }
        }

        System.out.println(trapCount + " " + unreachableCount);

        sc.close();
    }
}
