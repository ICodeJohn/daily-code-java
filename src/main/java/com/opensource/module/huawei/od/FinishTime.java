package com.opensource.module.huawei.od;

import java.util.*;

public class FinishTime {
    static class Machine {
        int B, J;
        Machine(int b, int j) {
            B = b;
            J = j;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();

        for (int m = 0; m < M; m++) {
            int N = sc.nextInt();
            Machine[] machines = new Machine[N];
            for (int i = 0; i < N; i++) {
                int b = sc.nextInt();
                int j = sc.nextInt();
                machines[i] = new Machine(b, j);
            }

            // 按 J 从大到小排序
            Arrays.sort(machines, (a, b) -> b.J - a.J);

            int configSum = 0;
            int ans = 0;
            for (Machine machine : machines) {
                configSum += machine.B;
                int finishTime = configSum + machine.J;
                if (finishTime > ans) {
                    ans = finishTime;
                }
            }

            System.out.println(ans);
        }
    }
}