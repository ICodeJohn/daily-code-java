package com.opensource.module.huawei.od;

import java.util.*;

public class PinballGame_016 {

    static int splitCount = 0;
    static int leafCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N <= 0) {
            System.out.println("0 0");
            return;
        }

        split(N);

        System.out.println(splitCount + " " + leafCount);
    }

    static void split(int n) {

        if (n <= 2) {
            leafCount++;
            return;
        }

        splitCount++;

        int a = n / 2;
        int b = n - a;  // 等价于 n%2==0 ? n/2 : n/2+1

        split(a);
        split(b);
    }
}
