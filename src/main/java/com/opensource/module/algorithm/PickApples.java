package com.opensource.module.algorithm;

/**
 * @Title: "捡苹果"
 * @Description: Alice 和 Bob 在一个漂亮的果园里面工作，果园里面有N棵苹果树排成了一排，这些苹果树被标记成1 - N号。
 * Alice 计划收集连续的K棵苹果树上面的所有苹果，Bob计划收集连续的L棵苹果树上面的所有苹果。
 * Alice和Bob选择的区间不可以重合，你需要返回他们能够最大收集的苹果数量。
 * N 是整数且在以下范围内：[2，600]
 * K 和 L 都是整数且在以下范围内：[1，N-1]
 * 数组A的每个元素都是以下范围内的整数：[1，500]
 *
 *
 * 示例 1:
 * 输入:
 * A = [6, 1, 4, 6, 3, 2, 7, 4]
 * K = 3
 * L = 2
 * 输出: 24
 * 解释：因为Alice 可以选择3-5颗树然后摘到4 + 6 + 3 = 13 个苹果， Bob可以选择7-8棵树然后摘到7 + 4 = 11个苹果，因此他们可以收集到13 + 11 = 24。
 *
 * 示例 2:
 * 输入:
 * A = [10, 19, 15]
 * K = 2
 * L = 2
 * 输出: -1
 * 解释：因为对于 Alice 和 Bob 不能选择两个互不重合的区间。
 *
 * @Author: ZhaoWei
 * @Date: 2023/7/20 15:48
 * @Version V1.0
 */
public class PickApples {


    int solution(int[] a, int k, int l) {
        int n = a.length;
        if (k + l > n) {
            return -1;
        }
        int[] previousSum = new int[n];
        previousSum[0] = a[0];
        //获取前n个坑位的总合
        for (int i = 1; i < n; i++) {
            previousSum[i] = previousSum[i - 1] + a[i];
        }
        //有两种可能 前k后l 或者前l后k
        int[] previousK = new int[n];
        int[] previousL = new int[n];
        int[] postK = new int[n];
        int[] postL = new int[n];

        //设置前K或者L个最大值
        for (int i = 0; i < n; i++) {
            if (i == k - 1) {
                previousK[i] = range(previousSum, 0, i);
            } else if (i > k - 1) {
                previousK[i] = Math.max(range(previousSum, i - k + 1, i), previousK[i - 1]);
            }
            if (i == l - 1) {
                previousL[i] = range(previousSum, 0, i);
            } else if (i > l - 1) {
                previousL[i] = Math.max(range(previousSum, i - l + 1, i), previousK[i - 1]);
            }
        }
        //设置后L或者L个最大值
        for (int i = n - 1; i >= 0; i--) {
            if (i + k - 1 == n - 1) {
                postK[i] = range(previousSum, i, n - 1);
            } else if (i + k - 1 < n - 1) {
                postK[i] = Math.max(range(previousSum, i, i + k - 1), postK[i + 1]);
            }
            if (i + l - 1 == n - 1) {
                postL[i] = range(previousSum, i, n - 1);
            } else if (i + l - 1 < n - 1) {
                postL[i] = Math.max(range(previousSum, i, i + l - 1), postL[i + 1]);
            }
        }
        int result = 0;
        //按分界点分别计算
        for (int i = 0; i < n - 1; i++) {
            result = Math.max(result, previousK[i] + postL[i + 1]);
            result = Math.max(result, previousL[i] + postK[i + 1]);
        }
        return result;
    }

    /**
     * 获取区域值
     *
     * @param previousSum
     * @param left
     * @param right
     * @return
     */
    private int range(int[] previousSum, int left, int right) {
        if (left == 0) {
            return previousSum[right];
        }
        return previousSum[right] - previousSum[left - 1];
    }

    public static void main(String[] args) {
        PickApples pickApples = new PickApples();
        int result = pickApples.solution(new int[]{6, 1, 4, 6, 3, 2, 7, 4}, 3, 2);
        System.out.println(result);
    }
}
