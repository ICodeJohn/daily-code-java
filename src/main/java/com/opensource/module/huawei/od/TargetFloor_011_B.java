package com.opensource.module.huawei.od;

import java.util.Arrays;
import java.util.Scanner;

public class TargetFloor_011_B {


    static int target;
    static int n;
    static int[] nums;

    static boolean[] used;
    static int bestFloor = Integer.MIN_VALUE;
    static int[] bestSeq;
    static boolean foundExact = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        target = sc.nextInt();
        n = sc.nextInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        // 按题意：先处理大值
        Arrays.sort(nums);
        reverse(nums);

        used = new boolean[n];
        bestSeq = new int[n];

        dfs(0, 0, new int[n]);

        // 输出
        if (bestFloor == Integer.MIN_VALUE) {
            System.out.println("NULL");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(bestSeq[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }

    // reverse array
    static void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
            l++; r--;
        }
    }

    static void dfs(int idx, int floor, int[] path) {
        if (foundExact) return;

        if (idx == n) {
            if (floor == target) {
                foundExact = true;
                System.arraycopy(path, 0, bestSeq, 0, n);
                bestFloor = target;
            }
            else if (floor < target && floor > bestFloor) {
                bestFloor = floor;
                System.arraycopy(path, 0, bestSeq, 0, n);
            }
            return;
        }

        // 剪枝：即使全部向上也达不到 target
//        int maxUp = 0;
//        for (int i = 0; i < n; i++) if (!used[i]) maxUp += nums[i];
//        if (floor + maxUp < target) return;

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            used[i] = true;
            path[idx] = nums[i];

            int newFloor;
            if (idx % 2 == 0) newFloor = floor + nums[i];
            else newFloor = floor - nums[i];

            // 剪枝：楼层太负远离目标（可调）
//            if (newFloor < -200) {
//                used[i] = false;
//                continue;
//            }

            dfs(idx + 1, newFloor, path);

            used[i] = false;

            if (foundExact) return;
        }
    }
}
