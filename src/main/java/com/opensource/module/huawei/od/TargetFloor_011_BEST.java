package com.opensource.module.huawei.od;

import java.util.*;

public class TargetFloor_011_BEST {

    static int totalSum;
    static List<Integer> res = new ArrayList<>();
    static int minDiff;
    static int m, n;


    // 递归回溯枚举 选取exceptCount的数
    static void DFS(List<Integer> ans, int index, int currentSum, int count, int maxUpCount, int visited) {
        if (count > maxUpCount) {
            return;
        }
        if (maxUpCount == count) {
            // 净增楼层
            int diff = currentSum - (totalSum - currentSum);
            // 上升超过m的不考虑
            if (diff > m) {
                return;
            }
            // 找最小差距
            diff = Math.abs(diff - m);
            if (diff < minDiff) {
                res.clear();
                minDiff = diff;
                res.add(visited);
            } else if (diff == minDiff) {
                res.add(visited);
            }
            return;
        }

        for (int i = index; i < n; i++) {
            int num = ans.get(i);
            int nextCurrentSum = currentSum + num;
            // 剪枝 最终会超过m层
            if (nextCurrentSum - (totalSum - nextCurrentSum) > m) {
                continue;
            }
            // 递归回溯
            visited |= 1 << (n - i - 1);
            DFS(ans, i + 1, nextCurrentSum, count + 1, maxUpCount, visited);
            visited &= ~(1 << (n - i - 1));
        }
    }

    // 交叉构建结果集
    static List<Integer> buildArr(List<Integer> ans, int num) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = (1 & (num >> (n - 1 - i))) == 1;
        }
        List<Integer> s = new ArrayList<>(Collections.nCopies(n, 0));
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                s.set(pos, ans.get(i));
                pos += 2;
            }
        }
        pos = 1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                s.set(pos, ans.get(i));
                pos += 2;
            }
        }
        return s;
    }

    // 在所有mask生成的数组中，找出字典序最大的
    static List<Integer> findBestArray(List<Integer> ans) {
        List<Integer> best = new ArrayList<>();
        for (int mask : res) {
            List<Integer> curr = buildArr(ans, mask);
            if (best.isEmpty() || compare(curr, best) > 0) {
                best = curr;
            }
        }
        return best;
    }

    // 手动比较两个列表的字典序
    static int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        List<Integer> ans = new ArrayList<>();
        totalSum = 0;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            ans.add(x);
            totalSum += x;
        }
        minDiff = totalSum + m;
        // 从大到小排序
        ans.sort((a, b) -> b - a);

        int maxUpCount = (n + 1) / 2;
        int status = 0;
        DFS(ans, 0, 0, 0, maxUpCount, status);

        List<Integer> answer = findBestArray(ans);
        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i));
            if (i != answer.size() - 1) {
                System.out.print(" ");
            }
        }

    }
}
