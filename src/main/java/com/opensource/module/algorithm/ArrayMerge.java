package com.opensource.module.algorithm;

import java.util.Arrays;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/23 20:06
 * @Version V1.0
 */
public class ArrayMerge {

    public static void solution(int[] nums1, int m, int[] nums2, int n) {

        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;

        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = sorted[i];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        solution(nums1,3,nums2,3);
        System.out.println(Arrays.toString(nums1));

    }
}
