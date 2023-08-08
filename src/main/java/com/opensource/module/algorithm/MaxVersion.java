package com.opensource.module.algorithm;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/8/9 05:27
 * @Version V1.0
 */
public class MaxVersion {

    public static void main(String[] args) {

        PriorityQueue<Version> queue = new PriorityQueue<>();
        Version version1 = parse("1.2.3");
        Version version2 = parse("3.2.1");

        queue.offer(version1);
        queue.offer(version2);
        Version v = queue.peek();
        System.out.println(queue.stream().findFirst().toString());
        try (Scanner scanner = new Scanner(System.in)) {
            String v1 = scanner.nextLine();
            String v2 = scanner.nextLine();
            String res = solution(v1, v2);
            System.out.println(res);
        }
    }

    private static class Version implements Comparable<Version> {
        int fst;
        int scd;
        int inc;
        String mil = "";

        @Override
        public int compareTo(Version o) {
            if (this.fst != o.fst) {
                return Integer.compare(this.fst , o.fst);
            } else if (this.scd != o.scd) {
                return Integer.compare(this.scd , o.scd);
            } else if (this.inc != o.inc) {
                return Integer.compare(this.inc , o.inc);
            } else {
                return this.mil.compareTo(o.mil);
            }
        }

    }

    private static String solution(String v1, String v2) {
        Version ver1 = parse(v1);
        Version ver2 = parse(v2);
        int c = ver1.compareTo(ver2);
        if (c == 0) {
            return v1.length() >= v2.length() ? v1 : v2;
        } else {
            return c > 0 ? v1 : v2;
        }
    }

    private static Version parse(String ver) {
        String[] spl1 = ver.split("\\.");
        Version v = new Version();
        v.fst = Integer.parseInt(spl1[0]);
        v.scd = Integer.parseInt(spl1[1]);
        if (spl1.length > 2) {
            String[] spl2 = spl1[2].split("-");
            v.inc = Integer.parseInt(spl2[0]);
            if (spl2.length > 1) {
                v.mil = spl2[1];
            }
        }
        return v;
    }
}
