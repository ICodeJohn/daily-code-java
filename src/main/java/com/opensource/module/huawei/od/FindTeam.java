package com.opensource.module.huawei.od;

import java.util.Scanner;

public class FindTeam {

    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int m = sc.nextInt();

        if (n < 1 || n > 100000 || m < 1 || m > 100000) {
            System.out.println("NULL");
            return;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if (a < 1 || b < 1 || a > n || b > n) {
                System.out.println("da pian zi");
                continue;
            }
            if (c == 0) {
                union(a, b);
            } else if (c == 1) {
                if (find(a) == find(b)) {
                    System.out.println("we are a team");
                } else {
                    System.out.println("we are not a team");
                }
            } else {
                System.out.println("da pian zi");
            }
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}
