package com.opensource.module.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Title: ''
 * @Description: ''
 * @Author: ZhaoWei
 * @Date: 2023/7/29 09:55
 * @Version V1.0
 */
public class SurroundedArea {


    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        int row = scanner1.nextInt();
        int col = scanner1.nextInt();
        Scanner scanner2 = new Scanner(System.in);
        scanner2.nextLine();
        Character[][] matrix = new Character[row][col];
        for (int i = 0; i < row; i++) {
            String[] str = scanner2.nextLine().split(" ");
            for (int j = 0; j < col; j++) {
                matrix[i][j] = str[i].charAt(j);
            }
        }
        int maxArea = 0;
        HashMap<String, Integer> zones = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 'O' && (i == 0 || j == 0 || i == row - 1 || j == col - 1)) {
                    int calculate = calculate(copy(matrix, row, col), i, j, true);
                    if (calculate > 0) {
                        zones.put(i + " " + j, calculate);
                        maxArea = Math.max(calculate, maxArea);
                    }
                }
            }
        }

        String entrace = "";
        for (Map.Entry<String, Integer> entry : zones.entrySet()) {
            if (maxArea == entry.getValue() && entrace.isBlank()) {
                entrace = entry.getKey() + " " + entry.getValue();
            } else if (maxArea == entry.getValue() && !entrace.isBlank()) {
                entrace = entry.getValue().toString();
                break;
            }
        }
        if (entrace.isBlank()) {
            System.out.println("NULL");
        } else {
            System.out.println(entrace);
        }

    }

    private static Character[][] copy(Character[][] source, int row, int col) {
        Character[][] dest = new Character[row][col];
        for (int i = 0; i < row; i++) {
            dest[i] = Arrays.copyOf(source[i], col);
        }
        return dest;
    }

    private static int calculate(Character[][] matrix, int i, int j, boolean flag) {
        if (!flag) {
            if (i == 0 || j == 0 || i == matrix.length - 1 || j == matrix[0].length - 1) {
                return 0;
            }
        }
        matrix[i][j] = 'X';
        int count = 1;
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int r = 0; r < directions.length; r++) {
            int newX = i + directions[r][0];
            int newY = j + directions[r][1];
            if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && matrix[newX][newY] == 'O') {
                int calc = calculate(matrix, newX, newY, false);
                if (calc == 0) {
                    return 0;
                }
                count += calc;
            }
        }
        return count;
    }
}
