package com.opensource.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/24 21:49
 * @Version V1.0
 */
public class MyTest {

    List<Integer> ans = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    int count = 0;
    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        dfs(k, n, used);
        StringBuilder sb = new StringBuilder();
        for (Integer item : ans) {
            sb.append(item);
        }
        return sb.toString();
    }

    public void dfs(int k, int n, boolean[] used) {
        if (cur.size() == n) {
            count ++;
            if (count == k) {
                ans = new ArrayList<>(cur);
            }
            return ;
        }
        for (int i = 1; i <= n; i ++) {
            if (used[i]) {
                continue;
            }
            cur.add(i);
            used[i] = true;
            dfs(k, n, used);
            cur.remove(cur.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        myTest.getPermutation(3,1);
    }
}
