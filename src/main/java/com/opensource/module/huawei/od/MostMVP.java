package com.opensource.module.huawei.od;

import java.util.*;

public class MostMVP {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = 50;//sc.nextInt();
        Integer[] scores = new Integer[num];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < num; i++) {
            int score = 1;sc.nextInt();
            scores[i] = score;
            sum += score;
            max = Math.max(max, score);
        }
        Arrays.sort(scores, Comparator.reverseOrder());

        for (int i = max; i <= sum; i++) {
            if (sum % i != 0) {
                continue;
            }
            int k = sum / i;
            int[] buckets = new int[k];
            if (canPartition(scores, 0, buckets, i)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(sum);
    }

    static boolean canPartition(Integer[] scores, int idx, int[] buckets, int target) {
        if (idx == scores.length) {
            return true;
        }
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + scores[idx] <= target) {
                if (i > 0 && buckets[i] == buckets[i - 1]) {
                    continue;
                }
                buckets[i] += scores[idx];
                if (canPartition(scores, idx + 1, buckets, target)) {
                    return true;
                }
                buckets[i] -= scores[idx];
            }
            if (buckets[i] == 0) {
                break;
            }
        }
        return false;
    }

}
