package com.opensource.module.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/27 18:30
 * @Version V1.0
 */
public class TaskMixingDepartment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] nums = new int[50000];
        for (int i = 0; i < count; ++i) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int tasks = scanner.nextInt();
            for (int j = start; j < end; ++j) {
                nums[j] += tasks;
            }
        }
        Arrays.sort(nums);
        System.out.println(nums[49999]);
    }
}
