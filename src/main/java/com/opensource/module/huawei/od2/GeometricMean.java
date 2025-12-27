package com.opensource.module.huawei.od2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GeometricMean {

    static int N, L;
    static double[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        nums = new double[N];
        double left = Double.MAX_VALUE;
        double right = Double.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            nums[i] = Double.parseDouble(br.readLine());
            left = Math.min(left, nums[i]);
            right = Math.max(right, nums[i]);
        }

        // 避免 log(0)
        //if (left <= 0) left = 1e-9;

        // 浮点二分，精度足够
        for (int i = 0; i < 60; i++) {
            double mid = (left + right) / 2;
            if (check(mid)) left = mid;
            else right = mid;
        }

        Result res = getResult(left);
        System.out.println(res.start + " " + res.len);
    }

    // 检查是否存在长度 >= L 的子数组几何平均 >= x
    static boolean check(double x) {
        double[] pre = new double[N + 1];
        for (int i = 1; i <= N; i++) {
            pre[i] = pre[i - 1] + Math.log(nums[i - 1]) - Math.log(x);
        }
        double minPre = 0;
        for (int i = L; i <= N; i++) {
            minPre = Math.min(minPre, pre[i - L]);
            if (pre[i] - minPre >= 0) return true;
        }
        return false;
    }

    // 找出具体起点和长度
    static Result getResult(double x) {
        double[] pre = new double[N + 1];
        for (int i = 1; i <= N; i++) {
            pre[i] = pre[i - 1] + Math.log(nums[i - 1]) - Math.log(x);
        }
        int minPos = 0;
        double minPre = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestStart = 0; // 注意输出下标从0开始

        for (int i = L; i <= N; i++) {
            if (pre[i - L] < minPre) {
                minPre = pre[i - L];
                minPos = i - L;
            }
            if (pre[i] - minPre >= 0) {
                int len = i - minPos;
                int start = minPos;
                if (len < bestLen || (len == bestLen && start < bestStart)) {
                    bestLen = len;
                    bestStart = start;
                }
            }
        }

        return new Result(bestStart, bestLen);
    }

    static class Result {
        int start, len;

        Result(int start, int len) {
            this.start = start;
            this.len = len;
        }
    }
}


