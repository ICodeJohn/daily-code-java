package com.opensource.module.huawei.od;

import java.util.*;

public class Square_021 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] points = new int[n][2];
        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
            set.add(points[i][0] + "," + points[i][1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                int dx = x2 - x1;
                int dy = y2 - y1;

                // 第一种旋转方向
                int x3 = x1 - dy, y3 = y1 + dx;
                int x4 = x2 - dy, y4 = y2 + dx;
                if (set.contains(x3 + "," + y3) && set.contains(x4 + "," + y4)) {
                    count++;
                }


                // 第二种旋转方向
                x3 = x1 + dy; y3 = y1 - dx;
                x4 = x2 + dy; y4 = y2 - dx;
                if (set.contains(x3 + "," + y3) && set.contains(x4 + "," + y4)) {
                    count++;
                }
            }
        }

        System.out.println(count / 4);
    }
}
