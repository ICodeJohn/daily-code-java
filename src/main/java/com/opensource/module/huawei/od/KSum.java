package com.opensource.module.huawei.od;

import java.util.*;

public class KSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        String[] numsStr = scanner.nextLine().split(" ");
        int[] nums = new int[numsStr.length];
        for (int i = 0; i < numsStr.length; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
        }

        int k = scanner.nextInt();
        int target = scanner.nextInt();

        // 计算并输出结果
        int result = kSum(nums, k, target);
        System.out.println(result);

        scanner.close();
    }

    public static int kSum(int[] nums, int k, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, k, target, 0, new ArrayList<>(), res);
        return res.size();
    }

    private static void dfs(int[] nums, int k, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (k == 2) {
            // 两数之和，双指针
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    // 找到匹配
                    List<Integer> temp = new ArrayList<>(path);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    left++;
                    right--;
                    // 跳过重复
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        } else {
            // k > 2，固定一个数，递归
            for (int i = start; i < nums.length; i++) {
                // 跳过重复
                if (i > start && nums[i] == nums[i - 1]) continue;
                // 剪枝：如果当前数太大，后面不可能
                if (target - nums[i] < nums[i] * (k - 1)) break;
                path.add(nums[i]);
                dfs(nums, k - 1, target - nums[i], i + 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
