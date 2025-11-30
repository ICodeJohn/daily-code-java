package com.opensource.module.huawei.od;

import java.util.*;

public class BitErrorRate {

    // 解析压缩字符串，返回 (count, char) 列表
    private static List<Pair> parse(String s) {
        List<Pair> list = new ArrayList<>();
        int n = s.length();
        int i = 0;

        while (i < n) {
            int j = i;
            // 多位数字
            while (j < n && Character.isDigit(s.charAt(j))) {
                j++;
            }
            int count = Integer.parseInt(s.substring(i, j));
            char c = s.charAt(j);
            list.add(new Pair(count, c));
            i = j + 1;
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.next();
        String s2 = sc.next();

        List<Pair> a = parse(s1);
        List<Pair> b = parse(s2);

        long wrong = 0;
        long total = 0;

        int i = 0, j = 0;
        long ca = 0, cb = 0; // 当前剩余计数

        while (i < a.size() && j < b.size()) {

            if (ca == 0) ca = a.get(i).count;
            if (cb == 0) cb = b.get(j).count;

            long take = Math.min(ca, cb);

            // 总长度增加
            total += take;

            // 字符不同则增加错误数
            if (a.get(i).ch != b.get(j).ch) {
                wrong += take;
            }

            // 消耗当前段
            ca -= take;
            cb -= take;

            if (ca == 0) i++;
            if (cb == 0) j++;
        }

        System.out.println(wrong + "/" + total);
    }

    // count + char 对
    static class Pair {
        long count;
        char ch;
        Pair(long c, char h) {
            count = c;
            ch = h;
        }
    }
}
