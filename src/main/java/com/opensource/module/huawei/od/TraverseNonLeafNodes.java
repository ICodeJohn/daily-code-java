package com.opensource.module.huawei.od;

import java.util.*;

public class TraverseNonLeafNodes {

    static int[] arr;
    static int n;
    static List<Integer> out = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读整行并拆分
        String line = "";
        if (!sc.hasNextLine()) return;
        line = sc.nextLine().trim();
        if (line.length() == 0) {
            System.out.println();
            return;
        }
        String[] parts = line.split("\\s+");
        n = parts.length;
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(parts[i]);

        postOrderNonLeaf(0);

        // 打印结果（空格分隔）
        for (int i = 0; i < out.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(out.get(i));
        }
        System.out.println();
    }

    // 判断下标 i 是否为“非叶子”节点
    static boolean isNonLeaf(int i) {
        if (i < 0 || i >= n) return false;
        if (n == 1) return true; // 题目明确：单节点当作非叶子
        return 2 * i + 1 < n; // 有左孩子即视为非叶子
    }

    // 对“只含非叶子节点的树”做后序遍历（左 右 根）
    static void postOrderNonLeaf(int i) {
        if (i < 0 || i >= n) return;
        if (!isNonLeaf(i)) return; // 如果是叶子（或越界），忽略
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (isNonLeaf(left)) postOrderNonLeaf(left);
        if (isNonLeaf(right)) postOrderNonLeaf(right);
        out.add(arr[i]);
    }
}
