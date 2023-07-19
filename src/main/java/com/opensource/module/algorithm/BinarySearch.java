package com.opensource.module.algorithm;

/**
 * @Title: "二分查找法"
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/19 13:47
 * @Version V1.0
 */
public class BinarySearch {

    int solution(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        int left = 0, right = len - 1;
        while (right >= left) {
            int subLen =  (right-left) / 2 +left;
            if (nums[subLen] == target) {
                return subLen;
            }
            if (nums[subLen] > target) {
                right = subLen - 1;
            } else {
                left = subLen + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch  search = new BinarySearch();
        int result = search.solution(new int[]{-1,0,3,5,9,12}, 2);
        System.out.println(result);
    }
}
