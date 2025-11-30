package com.opensource.module.huawei.od;

import java.util.*;

public class ProductSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 读取排序方向：1表示降序，-1表示升序
        int[] directions = new int[m];
        for (int i = 0; i < m; i++) {
            directions[i] = scanner.nextInt();
        }

        // 读取商品属性
        int[][] products = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                products[i][j] = scanner.nextInt();
            }
        }

        // 自定义排序
        Arrays.sort(products, (a, b) -> {
            for (int i = 0; i < m; i++) {
                if (a[i] != b[i]) {
                    // 根据排序方向决定比较结果
                    if (directions[i] == 1) {
                        // 降序：b[i] - a[i]
                        return b[i] - a[i];
                    } else {
                        // 升序：a[i] - b[i]
                        return a[i] - b[i];
                    }
                }
            }
            return 0; // 所有属性都相同
        });

        // 输出结果
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(products[i][j]);
                if (j < m - 1) System.out.print(" ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
