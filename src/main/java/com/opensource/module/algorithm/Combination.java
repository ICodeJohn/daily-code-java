package com.opensource.module.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 有超时限制---
 *
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/8/3 19:04
 * @Version V1.0
 */
public class Combination {

    List<Integer> ans = new ArrayList<>();
    int count = 0;

    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        dfs(k, n, 0,new ArrayList<Integer>());
        StringBuilder sb = new StringBuilder();
        for (Integer item : ans) {
            sb.append(item);
        }
        return sb.toString();
    }

    public void dfs(int k, int n, int idx, List<Integer> path) {
        if (path.size() == n) {
            count++;
            if (count == k) {
                ans = new ArrayList<>(path);
            }
            return;
        }
        for (int i = 1; i <= n; i++) {
            if(path.contains(i)){
                continue;
            }
            path.add(i);
            dfs(k, n, idx + 1, path);
            path.remove(path.size() - 1);
        }
    }

}
