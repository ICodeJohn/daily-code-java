package com.opensource.module.huawei.od;

import java.util.Scanner;

public class Spiral {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 填充数字数量
        int m = sc.nextInt(); // 行数

        int cols = (n + m - 1) / m;  // 最小列数

        int[][] matrix = new int[m][cols]; // 0 表示未填

        int top = 0, bottom = m - 1, left = 0, right = cols - 1;
        int num = 1;

        while (num <= n) {

            // 上行 left -> right
            for (int i = left; i <= right && num <= n; i++)
                matrix[top][i] = num++;
            top++;

            // 右列 top -> bottom
            for (int i = top; i <= bottom && num <= n; i++)
                matrix[i][right] = num++;
            right--;

            // 下行 right -> left
            for (int i = right; i >= left && num <= n; i--)
                matrix[bottom][i] = num++;
            bottom--;

            // 左列 bottom -> top
            for (int i = bottom; i >= top && num <= n; i--)
                matrix[i][left] = num++;
            left++;
        }

        // 输出
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0)
                    System.out.print("*");
                else
                    System.out.print(matrix[i][j]);

                if (j < cols - 1) System.out.print(" ");
            }
            System.out.println();
        }

        sc.close();
    }
}
