package com.opensource.module.huawei.od;

import java.util.Scanner;

public class FertilizedOrchard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long d = sc.nextLong();
        long[] fields = new long[n];
        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            fields[i] = sc.nextInt();
            max = Math.max(max, fields[i]);
        }
        if (d < fields.length) {
            System.out.println(-1);
            return;
        }
        long left = 1, right = max;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (canFinished(mid, d, fields)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);

    }

    private static boolean canFinished(long k, long d, long[] fields) {
        long sum = 0;
        for (long field : fields) {
            sum += (field + k - 1) / k;
        }
        return sum <= d;
    }

}
