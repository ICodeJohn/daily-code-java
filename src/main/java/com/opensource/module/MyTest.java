package com.opensource.module;

import java.util.*;

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
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        Map<Character,Integer> map = new HashMap<>(){{
           put('A',10);
            put('B',11);
            put('C',12);
            put('D',13);
            put('E',14);
            put('F',15);
        }};

            while (in.hasNext()) { // 注意 while 处理多个 case
                String a = in.next();
                a = a.replace("0x","");
                int num = 0;
                for (int i = 0; i < a.length(); ++i) {
                    char  ch = a.charAt(a.length() - i -1);
                    if(ch >= '0' && ch <='9'){
                        num += (ch-'0') * Math.pow(16,i);
                    }else{
                        num += map.get(ch) * Math.pow(16,i);
                    }
                }
                System.out.println(num);
            }
    }
}
