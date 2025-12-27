package com.opensource.module.huawei.od;

import java.util.*;

public class TrafficPeak_031 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split(" ");
        int n = parts.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        if(nums.length < 3){
            System.out.println("-1");
            return;
        }

        int[] left = new int[n];  // 左边第一个比它小的索引
        int[] right = new int[n]; // 右边第一个比它小的索引
        Arrays.fill(left, -1);
        Arrays.fill(right, n);

        // 单调栈找左边第一个比它小的
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        // 单调栈找右边第一个比它小的
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                right[i] = stack.peek();
            }
            stack.push(i);
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (left[j] != -1 && right[j] != n) {
                ans = Math.min(ans, right[j] - left[j]);
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
