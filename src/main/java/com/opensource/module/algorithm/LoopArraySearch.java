package com.opensource.module.algorithm;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/24 10:50
 * @Version V1.0
 */
public class LoopArraySearch {
    public static int search(int nums[]) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (right > left) {
            //找到中间元素，这个写法mid会偏左
            int mid = (right - left) / 2 + left;
            int leftNum = nums[left];
            int midNum = nums[mid];
            int rightNum = nums[right];
            //最终只会剩下两个元素
            if (left + 1 == right) {
                if (leftNum > rightNum) {
                    return right;
                }
                return 0;
            }
            //这里使用二分法则，则一定有一端元素右侧小于左侧
            if (midNum < leftNum) {
                right = mid;
            } else if (midNum > rightNum) {
                left = mid;
            } else {
                System.out.println();
                //如果没有符合条件元素则退出查找
                break;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int res = LoopArraySearch.search(new int[]{5,6,7,8,1,2,3});
        System.out.println(res);
    }

}
