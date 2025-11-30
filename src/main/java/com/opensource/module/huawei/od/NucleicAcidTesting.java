package com.opensource.module.huawei.od;

import java.util.*;

public class NucleicAcidTesting {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine(); // 换行

        // 读确诊人员
        String[] patientsStr = sc.nextLine().split(",");
        Set<Integer> confirmed = new HashSet<>();
        for (String s : patientsStr) {
            confirmed.add(Integer.parseInt(s));
        }

        // 读接触矩阵
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = sc.nextLine().split(",");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }

        // BFS
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();

        // 所有确诊病例入队
        for (int p : confirmed) {
            queue.offer(p);
            visited[p] = true;
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor = 0; neighbor < N; neighbor++) {
                if (matrix[curr][neighbor] == 1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // 统计需要检测的人（visited中为true的，除去确诊病例）
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i] && !confirmed.contains(i)) {
                count++;
            }
        }

        System.out.println(count);
    }

}
