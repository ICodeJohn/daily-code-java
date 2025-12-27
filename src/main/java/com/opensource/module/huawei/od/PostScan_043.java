package com.opensource.module.huawei.od;

import java.util.*;

public class PostScan_043 {
    // 判断是否为叶子节点
    private static boolean isLeaf(int pos, int n) {
        return (2 * pos + 1 >= n); // 没有左孩子就是叶子
    }

    // 递归遍历
    private static void dfs(List<String> ans, int n, int pos, List<String> res) {
        if (pos >= n || isLeaf(pos, n)) {
            return;
        }

        int left = 2 * pos + 1;
        int right = 2 * pos + 2;

        // 只在子节点是非叶子时才递归
        if (left < n && !isLeaf(left, n)) {
            dfs(ans, n, left, res);
        }
        if (right < n && !isLeaf(right, n)) {
            dfs(ans, n, right, res);
        }

        // 当前节点是非叶子节点
        res.add(ans.get(pos));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        scanner.close();

        List<String> res = new ArrayList<>();
        int n = arr.length;

        if (n == 1) {
            System.out.println(arr[0]);
            return;
        }


        dfs(Arrays.asList(arr), n, 0, res);
        System.out.println(String.join(" ", res));
    }
}
